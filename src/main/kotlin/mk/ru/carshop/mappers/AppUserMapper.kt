package mk.ru.carshop.mappers

import java.time.LocalDateTime
import mk.ru.carshop.enums.AppUserRole
import mk.ru.carshop.persistence.entities.AppUser
import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.responses.CarAppUserInfoResponse
import mk.ru.carshop.web.responses.AppUserRegisterResponse
import org.springframework.stereotype.Component

@Component
class AppUserMapper {
    fun toEntity(user: AppUserRegisterRequest): AppUser = AppUser(
        login = user.login,
        password = user.password,
        mail = user.mail,
        role = AppUserRole.USER,
        agreeReceiveMails = true,
        blocked = false
    )

    fun toRegisterResponse(appUser: AppUser): AppUserRegisterResponse = AppUserRegisterResponse(
        login = appUser.login!!,
        registrationDate = appUser.registrationDate!!
    )

    fun toInfoResponse(appUser: AppUser): CarAppUserInfoResponse = CarAppUserInfoResponse(
        login = appUser.login!!,
        mail = appUser.mail!!,
        blocked = appUser.blocked
    )
}