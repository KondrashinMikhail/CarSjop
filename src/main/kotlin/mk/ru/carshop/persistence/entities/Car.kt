package mk.ru.carshop.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.UuidGenerator

@Entity
data class Car(
    @Id
    @UuidGenerator
    val id: UUID? = null,
    @Column(nullable = false)
    var manufacturer: String? = null,
    @Column(nullable = false)
    var model: String? = null,
    @Column(nullable = false)
    var creationDateTime: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false)
    var isDeleted: Boolean = false
)