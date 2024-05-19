package mk.ru.carshop.persistence.repositories

import java.util.UUID
import mk.ru.carshop.persistence.entities.Car
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, UUID> {
    fun findAllByIsDeleted(pageable: Pageable? = null, isDeleted: Boolean): Page<Car>
}