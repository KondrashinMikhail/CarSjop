package mk.ru.carshop.services.car

import jakarta.persistence.criteria.Predicate
import java.util.UUID
import mk.ru.carshop.exceptions.ContentNotFoundError
import mk.ru.carshop.exceptions.SoftDeletionException
import mk.ru.carshop.mappers.CarMapper
import mk.ru.carshop.persistence.entities.Car
import mk.ru.carshop.persistence.repositories.AppUserRepository
import mk.ru.carshop.persistence.repositories.CarRepository
import mk.ru.carshop.services.criteria.conditions.CommonCondition
import mk.ru.carshop.utils.AppUserInfo
import mk.ru.carshop.web.requests.CarCreateRequest
import mk.ru.carshop.web.requests.CarUpdateRequest
import mk.ru.carshop.web.responses.CarCreateResponse
import mk.ru.carshop.web.responses.CarInfoResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class CarServiceImpl(
    private val carRepository: CarRepository,
    private val appUserRepository: AppUserRepository,
    private val carMapper: CarMapper,
) : CarService {
    private final val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun searchCars(conditions: List<CommonCondition<Any>>?, pageable: Pageable?): Page<CarInfoResponse> {
        val specification: Specification<Car> = Specification<Car> { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()
            conditions?.forEach { condition ->
                predicates.add(
                    condition.operation.getPredicate(
                        predicateSpecification = condition.predicateSpecification,
                        expression = root.get(condition.field),
                        value = condition.value,
                        criteriaBuilder = criteriaBuilder
                    )
                )
            }
            criteriaBuilder.and(* predicates.toTypedArray())
        }
        val cars: Page<Car> = carRepository.findAll(specification, pageable ?: Pageable.unpaged())
        log.info("Found ${cars.totalElements} of cars ${conditions?.let { "with ${it.size} of" } ?: "without"} conditions")
        return cars.map { carMapper.toInfoResponse(it) }
    }

    override fun findCars(byOwner: Boolean?, showDeleted: Boolean?, pageable: Pageable?): Page<CarInfoResponse> {
        val login: String = AppUserInfo.getAuthenticatedLogin()
        val finalPageable = pageable ?: Pageable.unpaged()

        val cars: Page<Car> = when (showDeleted) {
            null, false -> when (byOwner) {
                null, false -> carRepository.findByDeletedFalse(finalPageable)
                true -> carRepository.findByDeletedFalseAndOwnerLogin(login = login, pageable = finalPageable)
            }

            true -> when (byOwner) {
                null, false -> carRepository.findAll(finalPageable)
                true -> carRepository.findByOwnerLogin(login = login, pageable = finalPageable)
            }
        }

        log.info("Found ${cars.totalElements} of available ${showDeleted ?: "and deleted"} cars ${byOwner ?: "and by owner '$login'"}")
        return cars.map { carMapper.toInfoResponse(it) }
    }

    override fun findById(id: UUID): CarInfoResponse {
        val car: Car = findEntityById(id)
        log.info("Found car with id - $id")
        return carMapper.toInfoResponse(car)
    }

    override fun createCar(carCreateRequest: CarCreateRequest): CarCreateResponse {
        val car = carMapper.toEntity(carCreateRequest)

        val authenticatedLogin: String = AppUserInfo.getAuthenticatedLogin()
        car.owner = appUserRepository.findById(authenticatedLogin)
            .orElseThrow { ContentNotFoundError("User with login - $authenticatedLogin not found") }

        val savedCar = carRepository.save(car)
        log.info("Created car with id - ${savedCar.id}")
        return carMapper.toCreateResponse(savedCar)
    }

    override fun updateCar(carUpdateRequest: CarUpdateRequest): CarInfoResponse {
        val car = findEntityById(id = carUpdateRequest.id, deletionCheck = true)
        AppUserInfo.checkAccessAllowed(car.owner?.login!!)

        carUpdateRequest.manufacturer?.let { car.manufacturer = it }
        carUpdateRequest.model?.let { car.model = it }
        carUpdateRequest.price?.let { car.price = it }

        val updatedCar = carRepository.save(car)
        log.info("Updated car with id - ${updatedCar.id}")
        return carMapper.toInfoResponse(updatedCar)
    }

    override fun deleteCar(id: UUID) {
        val car = findEntityById(id = id, deletionCheck = true)
        car.deleted = true
        carRepository.save(car)
        log.info("Deleted car with id - $id")
    }

    override fun restoreCar(id: UUID) {
        val car = findEntityById(id)
        when (car.deleted!!) {
            true -> car.deleted = false
            false -> throw SoftDeletionException("Car with id - $id is not deleted")
        }
        carRepository.save(car)
        log.info("Restored car with id - $id")
    }

    private fun findEntityById(id: UUID, deletionCheck: Boolean = false): Car {
        val car: Car = carRepository.findById(id).orElseThrow { ContentNotFoundError("Car with id - $id not found") }
        if (deletionCheck && car.deleted!!)
            throw SoftDeletionException("Car with id - $id not found")
        return car
    }
}