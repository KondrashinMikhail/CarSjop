package mk.ru.carshop.utils

import mk.ru.carshop.enums.AppUserRole
import mk.ru.carshop.exceptions.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder

object AppUserInfo {
    fun checkAccessAllowed(requiredLogin: String) {
        val securityContext = SecurityContextHolder.getContext().authentication
        if (securityContext.authorities.none { it.authority == AppUserRole.ADMIN.role })
            if (securityContext.name != requiredLogin)
                throw AccessDeniedException("Access for user with login - ${securityContext.name} is denied")
    }

    fun getAuthenticatedLogin(): String {
        return SecurityContextHolder.getContext().authentication.name
    }
}
