package mk.ru.shop.persistence.repositories

import java.util.Optional
import java.util.UUID
import mk.ru.shop.persistence.entities.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepo : JpaRepository<Wallet, UUID> {
    fun findByOwnerLogin(login: String): Optional<Wallet>
}