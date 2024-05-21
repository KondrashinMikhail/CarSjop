package mk.ru.carshop.web.controllers

import java.util.UUID
import mk.ru.carshop.services.car.CarService
import mk.ru.carshop.services.criteria.conditions.CommonCondition
import mk.ru.carshop.web.requests.CreateCarRequest
import mk.ru.carshop.web.requests.UpdateCarRequest
import mk.ru.carshop.web.responses.CarInfoResponse
import mk.ru.carshop.web.responses.CreateCarResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/car")
class CarController(private val carService: CarService) {
    @PostMapping("/find")
    fun findAll(
        @RequestBody(required = false) conditions: List<CommonCondition<Any>>?,
        @RequestParam(required = false) pageable: Pageable?
    ): ResponseEntity<Page<CarInfoResponse>> {
        return ResponseEntity.ok(carService.findAll(conditions = conditions, pageable = pageable))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<CarInfoResponse> {
        return ResponseEntity.ok(carService.findById(id))
    }

    @PostMapping
    fun createCar(@RequestBody createCarRequest: CreateCarRequest): ResponseEntity<CreateCarResponse> {
        return ResponseEntity.ok(carService.createCar(createCarRequest))
    }

    @PatchMapping
    fun updateCar(@RequestBody updateCarRequest: UpdateCarRequest): ResponseEntity<CarInfoResponse> {
        return ResponseEntity.ok(carService.updateCar(updateCarRequest))
    }

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: UUID): ResponseEntity<Unit> {
        return ResponseEntity.ok(carService.deleteCar(id))
    }

    @PutMapping("/restore/{id}")
    fun restoreCar(@PathVariable id: UUID): ResponseEntity<Unit> {
        return ResponseEntity.ok(carService.restoreCar(id))
    }

    @PutMapping("/sell/{id}")
    fun sellCar(@PathVariable id: UUID): ResponseEntity<Unit> {
        return ResponseEntity.ok(carService.sellCar(id))
    }
}