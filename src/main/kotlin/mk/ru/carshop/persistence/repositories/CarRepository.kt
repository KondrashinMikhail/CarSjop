package mk.ru.carshop.persistence.repositories

import java.util.UUID
import mk.ru.carshop.persistence.entities.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CarRepository : JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car>