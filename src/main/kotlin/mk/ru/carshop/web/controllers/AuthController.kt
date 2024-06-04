package mk.ru.carshop.web.controllers

import mk.ru.carshop.services.authentication.AuthenticationService
import mk.ru.carshop.web.requests.AuthenticationRequest
import mk.ru.carshop.web.responses.AuthenticationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
) {
    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(authenticationService.authentication(authRequest))

    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshToken: String): ResponseEntity<String> =
        authenticationService.refreshAccessToken(refreshToken)
            ?.let { ResponseEntity.ok(it) }
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

}