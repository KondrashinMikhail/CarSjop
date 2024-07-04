package mk.ru.shop.web.responses

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionInfoResponse(
    val id: UUID,
    val amount: BigDecimal,
    val date: LocalDateTime,
    val sender: TransactionWalletInfoResponse,
    val recipient: TransactionWalletInfoResponse,
    val product: TransactionProductInfoResponse,
)
