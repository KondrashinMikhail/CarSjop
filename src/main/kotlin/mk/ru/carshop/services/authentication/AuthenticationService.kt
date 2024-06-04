package mk.ru.carshop.services.authentication

import mk.ru.carshop.web.requests.AuthenticationRequest
import mk.ru.carshop.web.responses.AuthenticationResponse
import org.springframework.security.core.userdetails.UserDetails

interface AuthenticationService {
    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse
    fun createAccessToken(user: UserDetails): String
    fun refreshAccessToken(refreshToken: String): String?
}