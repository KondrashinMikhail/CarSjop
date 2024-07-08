package mk.ru.shop.services.wallet

import java.util.UUID
import mk.ru.shop.persistence.entities.AppUser
import mk.ru.shop.persistence.entities.Transaction
import mk.ru.shop.persistence.entities.Wallet
import mk.ru.shop.web.responses.wallet.WalletInfoResponse

interface WalletService {
    fun create(owner: AppUser): Wallet
    fun getFromBalance(transaction: Transaction, wallet: Wallet)
    fun addToBalance(transaction: Transaction, wallet: Wallet)
    fun findEntityById(id: UUID): Wallet
    fun findByAuthenticatedLogin(): WalletInfoResponse
}