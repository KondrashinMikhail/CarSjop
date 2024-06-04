package mk.ru.carshop.mappers

import mk.ru.carshop.persistence.entities.Car
import mk.ru.carshop.web.requests.CarCreateRequest
import mk.ru.carshop.web.responses.CarInfoResponse
import mk.ru.carshop.web.responses.CarCreateResponse
import org.springframework.stereotype.Component

@Component
class CarMapper {
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
    )

    fun toEntity(car: CarCreateRequest): Car = Car(
        manufacturer = car.manufacturer,
        model = car.model,
        price = car.price
    )
}