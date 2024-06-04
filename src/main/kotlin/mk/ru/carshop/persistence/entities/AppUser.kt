package mk.ru.carshop.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal
import java.util.UUID
import mk.ru.carshop.utils.Patterns
import mk.ru.carshop.enums.AppUserRole
import org.hibernate.annotations.UuidGenerator

@Entity
data class AppUser(
    @Id
    @UuidGenerator
    val id: UUID? = null,
    @Column(nullable = false, unique = true)
    @Pattern(regexp = Patterns.LOGIN_PATTERN)
    val login: String? = null,
    @Column(nullable = false)
    @Pattern(regexp = Patterns.PASSWORD_PATTERN)
    var password: String? = null,
    @Column(nullable = false, unique = true)
    @Pattern(regexp = Patterns.MAIL_PATTERN)
    val mail: String? = null,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: AppUserRole = AppUserRole.USER,
    @Column(nullable = false)
    val balance: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val isAgreeReceiveMails: Boolean? = true
)
