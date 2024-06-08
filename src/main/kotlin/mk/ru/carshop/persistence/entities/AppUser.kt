package mk.ru.carshop.persistence.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import mk.ru.carshop.enums.AppUserRole
import mk.ru.carshop.utils.Patterns
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity
data class AppUser(
    @Id
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
    val agreeReceiveMails: Boolean? = true,
    @Column(nullable = false)
    var blocked: Boolean = false,
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    var registrationDate: LocalDate? = LocalDate.now(),
    @OneToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "owner_login")
    var cars: List<Car>? = null
)
