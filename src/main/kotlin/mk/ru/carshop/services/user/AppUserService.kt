package mk.ru.carshop.services.user

import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.responses.AppUserRegisterResponse

interface AppUserService {
    fun register(registerRequest: AppUserRegisterRequest): AppUserRegisterResponse
}