package mk.ru.carshop.services.car

import java.util.UUID
import mk.ru.carshop.services.criteria.conditions.CommonCondition
import mk.ru.carshop.web.requests.CarCreateRequest
import mk.ru.carshop.web.requests.CarUpdateRequest
import mk.ru.carshop.web.responses.CarInfoResponse
import mk.ru.carshop.web.responses.CarCreateResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CarService {
    fun searchCars(conditions: List<CommonCondition<Any>>?, pageable: Pageable?): Page<CarInfoResponse>
    fun findCars(showDeleted: Boolean?, pageable: Pageable?): Page<CarInfoResponse>
    fun findById(id: UUID): CarInfoResponse
    fun createCar(carCreateRequest: CarCreateRequest): CarCreateResponse
    fun updateCar(carUpdateRequest: CarUpdateRequest): CarInfoResponse
    fun deleteCar(id: UUID)
    fun restoreCar(id: UUID)
}