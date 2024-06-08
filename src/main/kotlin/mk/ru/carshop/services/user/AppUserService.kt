package mk.ru.carshop.services.user

import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.requests.PasswordChangeRequest
import mk.ru.carshop.web.responses.AppUserRegisterResponse

interface AppUserService {
    fun register(registerRequest: AppUserRegisterRequest): AppUserRegisterResponse
    fun changePassword(login: String, passwordChangeRequest: PasswordChangeRequest)
    fun block(login: String)
    fun restore(login: String)
}