package mk.ru.shop.web.responses

import java.util.UUID

data class PaymentWalletInfoResponse(
    val id: UUID,
    val ownerLogin: String
)
