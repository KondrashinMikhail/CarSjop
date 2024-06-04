package mk.ru.carshop.services.user

import mk.ru.carshop.exceptions.ValidationException
import mk.ru.carshop.mappers.AppUserMapper
import mk.ru.carshop.persistence.entities.AppUser
import mk.ru.carshop.persistence.repositories.AppUserRepository
import mk.ru.carshop.utils.Patterns
import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.responses.AppUserRegisterResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AppUserServiceImpl(
    private val appUserRepository: AppUserRepository,
    private val appUserMapper: AppUserMapper,
    private val encoder: PasswordEncoder
) : AppUserService {
    private final val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun register(registerRequest: AppUserRegisterRequest): AppUserRegisterResponse {
        if (appUserRepository.existsByLoginOrMail(registerRequest.login, registerRequest.mail))
            throw ValidationException("User with such credentials already exists")

        if (!Patterns.loginRegex.matches(registerRequest.login))
            throw ValidationException("Login does not match regex")

        if (!Patterns.mailRegex.matches(registerRequest.mail))
            throw ValidationException("Mail does not match regex")

        if (!Patterns.passwordRegex.matches(registerRequest.password))
            throw ValidationException("Password does not match regex")

        val newAppUser: AppUser = appUserMapper.toEntity(registerRequest)
        newAppUser.password = encoder.encode(newAppUser.password).toString()
        val registeredUser = appUserRepository.save(newAppUser)
        log.info("Registered new user with id - ${registeredUser.id} and login - '${registeredUser.login}'")
        return appUserMapper.toRegisterResponse(registeredUser)
    }
}