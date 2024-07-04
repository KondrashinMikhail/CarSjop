package mk.ru.shop.web.responses

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionWalletInfoResponse(
    val id: UUID,
    val balance: BigDecimal,
    val lastModifiedDate: LocalDateTime,
)
