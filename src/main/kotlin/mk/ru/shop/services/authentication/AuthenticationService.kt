package mk.ru.shop.services.authentication

import mk.ru.shop.web.requests.AuthenticationRequest
import mk.ru.shop.web.responses.AuthenticationResponse
import mk.ru.shop.web.responses.RefreshTokenResponse
import org.springframework.security.core.userdetails.UserDetails

interface AuthenticationService {
    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse
    fun createAccessToken(user: UserDetails): String
    fun refreshAccessToken(refreshToken: String): RefreshTokenResponse?
}