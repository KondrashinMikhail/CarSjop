package mk.ru.shop.mappers

import mk.ru.shop.enums.AppUserRole
import mk.ru.shop.persistence.entities.AppUser
import mk.ru.shop.web.requests.AppUserRegisterRequest
import mk.ru.shop.web.responses.ProductAppUserInfoResponse
import mk.ru.shop.web.responses.AppUserRegisterResponse
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

    fun toInfoResponse(appUser: AppUser): ProductAppUserInfoResponse = ProductAppUserInfoResponse(
        login = appUser.login!!,
        mail = appUser.mail!!,
        blocked = appUser.blocked
    )
}