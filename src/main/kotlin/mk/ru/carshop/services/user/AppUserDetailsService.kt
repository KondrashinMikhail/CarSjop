package mk.ru.carshop.services.user

import mk.ru.carshop.exceptions.ContentNotFoundError
import mk.ru.carshop.exceptions.SoftDeletionException
import mk.ru.carshop.persistence.entities.AppUser
import mk.ru.carshop.persistence.repositories.AppUserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(private val appUserRepository: AppUserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val appUser: AppUser =
            appUserRepository.findById(username!!)
                .orElseThrow { ContentNotFoundError("User with login - $username not found") }

        if (appUser.blocked)
            throw SoftDeletionException("User with login - $username is blocked")

        return User.builder()
            .username(appUser.login)
            .password(appUser.password)
            .roles(appUser.role.name)
            .accountLocked(appUser.blocked)
            .build()
    }
}