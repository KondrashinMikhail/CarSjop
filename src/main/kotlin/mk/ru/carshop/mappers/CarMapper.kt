package mk.ru.carshop.mappers

import mk.ru.carshop.persistence.entities.Car
import mk.ru.carshop.web.requests.CarCreateRequest
import mk.ru.carshop.web.responses.CarCreateResponse
import mk.ru.carshop.web.responses.CarInfoResponse
import org.springframework.stereotype.Component

@Component
class CarMapper(
    private val appUserMapper: AppUserMapper
) {
    fun toCreateResponse(car: Car): CarCreateResponse = CarCreateResponse(
        id = car.id!!,
        registrationDate = car.registrationDate!!
    )

    fun toInfoResponse(car: Car): CarInfoResponse = CarInfoResponse(
        id = car.id!!,
        manufacturer = car.manufacturer!!,
        model = car.model!!,
        registrationDate = car.registrationDate!!,
        deleted = car.deleted!!,
        price = car.price!!,
        owner = appUserMapper.toInfoResponse(car.owner!!)
    )

    fun toEntity(car: CarCreateRequest): Car = Car(
        manufacturer = car.manufacturer,
        model = car.model,
        price = car.price
    )
}