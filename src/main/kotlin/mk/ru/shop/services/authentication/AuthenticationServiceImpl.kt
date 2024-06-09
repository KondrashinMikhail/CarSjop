package mk.ru.shop.services.authentication

import java.util.Date
import mk.ru.shop.configurations.JwtProperties
import mk.ru.shop.services.token.TokenService
import mk.ru.shop.services.user.AppUserDetailsService
import mk.ru.shop.utils.Jwt
import mk.ru.shop.web.requests.AuthenticationRequest
import mk.ru.shop.web.responses.AuthenticationResponse
import mk.ru.shop.web.responses.RefreshTokenResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDetailsService: AppUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) : AuthenticationService {
    override fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.login,
                authenticationRequest.password
            )
        )
        val user: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.login)
        val accessToken: String = createAccessToken(user)
        val refreshToken: String = createRefreshToken(user)
        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun refreshAccessToken(refreshToken: String): RefreshTokenResponse? {
        val subRefreshToken: String =
            if (refreshToken.startsWith(Jwt.PREFIX)) refreshToken.substringAfter(Jwt.PREFIX)
            else refreshToken

        val login: String = tokenService.getLoginFromToken(subRefreshToken)
        return login.let {
            val currentUserDetails: UserDetails = userDetailsService.loadUserByUsername(login)
            if (tokenService.isTokenValid(subRefreshToken, currentUserDetails))
                RefreshTokenResponse(
                    accessToken = createAccessToken(currentUserDetails),
                    refreshToken = createRefreshToken(currentUserDetails)
                )
            else null
        }
    }

    private fun createRefreshToken(user: UserDetails) = tokenService.generateToken(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    )

    override fun createAccessToken(user: UserDetails) = tokenService.generateToken(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )
}