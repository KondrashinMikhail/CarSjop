package mk.ru.shop.services.authentication

import mk.ru.shop.web.requests.AuthenticationRequest
import mk.ru.shop.web.responses.user.AuthenticationResponse

interface AuthenticationService {
    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse
}