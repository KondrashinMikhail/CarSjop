package mk.ru.carshop.mappers

import java.math.BigDecimal
import mk.ru.carshop.enums.AppUserRole
import mk.ru.carshop.persistence.entities.AppUser
import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.responses.AppUserRegisterResponse
import org.springframework.stereotype.Component

@Component
class AppUserMapper {
    fun toEntity(user: AppUserRegisterRequest): AppUser = AppUser(
        login = user.login,
        password = user.password,
        mail = user.mail,
        role = AppUserRole.USER,
        balance = BigDecimal.ZERO,
        isAgreeReceiveMails = true
    )

    fun toRegisterResponse(appUser: AppUser): AppUserRegisterResponse = AppUserRegisterResponse(
        id = appUser.id!!,
        login = appUser.login!!
    )
}