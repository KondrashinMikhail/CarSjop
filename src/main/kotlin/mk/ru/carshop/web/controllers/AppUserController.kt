package mk.ru.carshop.web.controllers

import mk.ru.carshop.services.user.AppUserService
import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.responses.AppUserRegisterResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class AppUserController(private val appUserService: AppUserService) {
    @PostMapping("/register")
    fun register(@RequestBody appUserRegisterRequest: AppUserRegisterRequest): ResponseEntity<AppUserRegisterResponse> =
        ResponseEntity.ok(appUserService.register(appUserRegisterRequest))
}