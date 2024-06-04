package mk.ru.carshop.persistence.repositories

import java.util.Optional
import java.util.UUID
import mk.ru.carshop.persistence.entities.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface AppUserRepository : JpaRepository<AppUser, UUID>, JpaSpecificationExecutor<AppUser> {
    fun existsByLoginOrMail(login: String, mail: String): Boolean
    fun findByLogin(login: String): Optional<AppUser>
}
