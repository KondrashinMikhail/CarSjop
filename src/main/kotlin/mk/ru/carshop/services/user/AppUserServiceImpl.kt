package mk.ru.carshop.services.user

import jakarta.transaction.Transactional
import mk.ru.carshop.exceptions.ContentNotFoundError
import mk.ru.carshop.exceptions.SoftDeletionException
import mk.ru.carshop.exceptions.ValidationException
import mk.ru.carshop.mappers.AppUserMapper
import mk.ru.carshop.persistence.entities.AppUser
import mk.ru.carshop.persistence.repositories.AppUserRepository
import mk.ru.carshop.utils.AppUserInfo
import mk.ru.carshop.utils.Patterns
import mk.ru.carshop.web.requests.AppUserRegisterRequest
import mk.ru.carshop.web.requests.PasswordChangeRequest
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

        val newUser = appUserMapper.toEntity(registerRequest)
        newUser.password = newUser.password?.let { encodePassword(it) }
        val registeredUser = appUserRepository.save(newUser)
        log.info("Registered new user with login - '${registeredUser.login}'")
        return appUserMapper.toRegisterResponse(registeredUser)
    }

    @Transactional
    override fun changePassword(login: String, passwordChangeRequest: PasswordChangeRequest) {
        AppUserInfo.checkAccessAllowed(login)

        val user = findEntityByLogin(login = login, blockedCheck = true)

        if (passwordChangeRequest.newPassword != passwordChangeRequest.newPasswordConfirm)
            throw ValidationException("Passwords are not equal")

        if (!Patterns.passwordRegex.matches(passwordChangeRequest.newPassword))
            throw ValidationException("Password does not match regex")

        user.password = encodePassword(passwordChangeRequest.newPassword)
        log.info("Changed password for user with login - '${user.login}'")
        appUserRepository.save(user)
    }

    override fun block(login: String) {
        AppUserInfo.checkAccessAllowed(login)

        val user = findEntityByLogin(login)
        when (user.blocked) {
            true -> throw SoftDeletionException("User with login - $login is already blocked")
            false -> user.blocked = true
        }
        appUserRepository.save(user)
        log.info("Blocked user with login - $login by user with login - ${AppUserInfo.getAuthenticatedLogin()}")
    }

    override fun restore(login: String) {
        AppUserInfo.checkAccessAllowed(login)

        val user = findEntityByLogin(login)
        when (user.blocked) {
            true -> user.blocked = false
            false -> throw SoftDeletionException("User with login - $login is not blocked")
        }
        appUserRepository.save(user)
        log.info("Restored user with id - $login")
    }

    private fun findEntityByLogin(login: String, blockedCheck: Boolean = false): AppUser {
        val user: AppUser = appUserRepository.findById(login)
            .orElseThrow { ContentNotFoundError("User with login - $login not found") }
        if (blockedCheck && user.blocked)
            throw SoftDeletionException("User with login - $login not found")
        return user
    }

    private fun encodePassword(password: String): String = encoder.encode(password).toString()
}