package mk.ru.carshop.persistence.repositories

import mk.ru.carshop.persistence.entities.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface AppUserRepository : JpaRepository<AppUser, String>, JpaSpecificationExecutor<AppUser> {
    fun existsByLoginOrMail(login: String, mail: String): Boolean
}
