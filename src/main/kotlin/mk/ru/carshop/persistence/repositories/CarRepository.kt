package mk.ru.carshop.persistence.repositories

import java.util.UUID
import mk.ru.carshop.persistence.entities.Car
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CarRepository : JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {
    fun findByDeletedFalseAndOwnerLogin(login: String, pageable: Pageable?): Page<Car>
    fun findByOwnerLogin(login: String, pageable: Pageable?): Page<Car>
    fun findByDeletedFalse(pageable: Pageable?): Page<Car>
}