package mk.ru.carshop.mappers

import mk.ru.carshop.persistence.entities.Car
import mk.ru.carshop.web.requests.CreateCarRequest
import mk.ru.carshop.web.responses.CarInfoResponse
import mk.ru.carshop.web.responses.CreateCarResponse
import org.springframework.stereotype.Component

@Component
class CarMapper {
    fun toCreateResponse(car: Car): CreateCarResponse {
        return CreateCarResponse(
            id = car.id!!,
            registrationDate = car.registrationDate
        )
    }

    fun toInfoResponse(car: Car): CarInfoResponse {
        return CarInfoResponse(
            id = car.id!!,
            manufacturer = car.manufacturer!!,
            model = car.model!!,
            registrationDate = car.registrationDate,
            isDeleted = car.isDeleted,
            price = car.price!!
        )
    }

    fun toEntity(car: CreateCarRequest): Car {
        return Car(
            manufacturer = car.manufacturer,
            model = car.model,
            price = car.price
        )
    }
}