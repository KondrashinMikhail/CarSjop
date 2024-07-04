package mk.ru.shop.services.transaction

import java.math.BigDecimal
import java.util.UUID
import mk.ru.shop.persistence.entities.Product
import mk.ru.shop.persistence.entities.Transaction
import mk.ru.shop.persistence.entities.Wallet

interface TransactionService {
    fun create(
        senderWallet: Wallet,
        recipientWallet: Wallet,
        amount: BigDecimal,
        feeAmount: BigDecimal,
        feePercent: BigDecimal,
        product: Product
    ): Transaction

    fun findEntityById(id: UUID): Transaction
}