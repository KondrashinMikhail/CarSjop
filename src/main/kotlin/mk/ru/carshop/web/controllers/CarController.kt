package mk.ru.carshop.web.controllers

import java.util.UUID
import mk.ru.carshop.services.car.CarService
import mk.ru.carshop.services.criteria.conditions.CommonCondition
import mk.ru.carshop.web.requests.CarCreateRequest
import mk.ru.carshop.web.requests.CarUpdateRequest
import mk.ru.carshop.web.responses.CarCreateResponse
import mk.ru.carshop.web.responses.CarInfoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/car")
class CarController(private val carService: CarService) {
    @PostMapping("/search")
    fun search(
        @RequestBody(required = false) conditions: List<CommonCondition<Any>>?,
        @RequestParam(required = false) pageable: Pageable?
    ): ResponseEntity<Page<CarInfoResponse>> =
        ResponseEntity.ok(
            carService.searchCars(
                conditions = conditions,
                pageable = pageable
            )
        )

    @GetMapping
    fun findCars(
        @RequestParam(required = false) deleted: Boolean?,
        @RequestParam(required = false) pageable: Pageable?
    ): ResponseEntity<Page<CarInfoResponse>> =
        ResponseEntity.ok(
            carService.findCars(
                showDeleted = deleted,
                pageable = pageable
            )
        )

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<CarInfoResponse> =
        ResponseEntity.ok(carService.findById(id))

    @PostMapping
    fun createCar(@RequestBody carCreateRequest: CarCreateRequest): ResponseEntity<CarCreateResponse> =
        ResponseEntity.ok(carService.createCar(carCreateRequest))

    @PatchMapping
    fun updateCar(@RequestBody carUpdateRequest: CarUpdateRequest): ResponseEntity<CarInfoResponse> =
        ResponseEntity.ok(carService.updateCar(carUpdateRequest))

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: UUID): ResponseEntity<Unit> =
        ResponseEntity.ok(carService.deleteCar(id))

    @PatchMapping("/{id}/restore")
    fun restoreCar(@PathVariable id: UUID): ResponseEntity<Unit> =
        ResponseEntity.ok(carService.restoreCar(id))
}