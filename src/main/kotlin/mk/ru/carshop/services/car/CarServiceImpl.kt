package mk.ru.carshop.services.car

import java.util.UUID
import mk.ru.carshop.conversion.CarMapper
import mk.ru.carshop.exceptions.ContentNotFoundError
import mk.ru.carshop.persistence.entities.Car
import mk.ru.carshop.persistence.repositories.CarRepository
import mk.ru.carshop.web.requests.CreateCarRequest
import mk.ru.carshop.web.requests.UpdateCarRequest
import mk.ru.carshop.web.responses.CarInfoResponse
import mk.ru.carshop.web.responses.CreateCarResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CarServiceImpl(
    private val carRepository: CarRepository,
    private val carMapper: CarMapper,
) : CarService {
    private final val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun findAll(pageable: Pageable): Page<CarInfoResponse> {
        val cars: Page<Car> = carRepository.findAll(pageable)
        log.info("Found ${cars.totalElements} of cars")
        return cars.map { carMapper.toInfoResponse(it) }
    }

    override fun findById(id: UUID): CarInfoResponse {
        val car: Car = findEntityById(id = id)
        log.info("Found car with id $id")
        return carMapper.toInfoResponse(car)
    }

    override fun createCar(createCarRequest: CreateCarRequest): CreateCarResponse {
        val car = carMapper.toEntity(createCarRequest)
        val savedCar = carRepository.save(car)
        log.info("Created car with id ${savedCar.id}")
        return carMapper.toCreateResponse(savedCar)
    }

    override fun updateCar(updateCarRequest: UpdateCarRequest): CarInfoResponse {
        val car = findEntityById(id = updateCarRequest.id)

        updateCarRequest.manufacturer?.let { car.manufacturer = it }
        updateCarRequest.model?.let { car.model = it }

        val updatedCar = carRepository.save(car)
        log.info("Updated car with id ${updatedCar.id}")
        return carMapper.toInfoResponse(updatedCar)
    }

    override fun deleteCar(id: UUID) {
        val car = findEntityById(id = id)
        car.isDeleted = true
        carRepository.save(car)
        log.info("Deleted car with id $id")
    }

    private fun findEntityById(id: UUID): Car {
        return carRepository.findById(id).orElseThrow { ContentNotFoundError("Car with id $id not found") }
    }
}