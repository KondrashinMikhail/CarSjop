package mk.ru.shop.web.responses.wallet

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import mk.ru.shop.web.responses.transaction.TransactionProductInfoResponse

data class WalletTransactionSenderInfoResponse(
    val id: UUID,
    val feePercent: BigDecimal,
    val feeAmount: BigDecimal,
    val amount: BigDecimal,
    val date: LocalDateTime,
    val totalAmount: BigDecimal,
    val recipientWallet: UUID,
    val product: TransactionProductInfoResponse?
)
