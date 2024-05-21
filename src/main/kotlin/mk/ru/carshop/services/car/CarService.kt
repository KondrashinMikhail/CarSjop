package mk.ru.carshop.services.car

import java.util.UUID
import mk.ru.carshop.services.criteria.conditions.CommonCondition
import mk.ru.carshop.web.requests.CreateCarRequest
import mk.ru.carshop.web.requests.UpdateCarRequest
import mk.ru.carshop.web.responses.CarInfoResponse
import mk.ru.carshop.web.responses.CreateCarResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CarService {
    fun findAll(conditions: List<CommonCondition<Any>>?, pageable: Pageable?): Page<CarInfoResponse>
    fun findById(id: UUID): CarInfoResponse
    fun createCar(createCarRequest: CreateCarRequest): CreateCarResponse
    fun updateCar(updateCarRequest: UpdateCarRequest): CarInfoResponse
    fun deleteCar(id: UUID)
    fun restoreCar(id: UUID)
    fun sellCar(id: UUID)
}