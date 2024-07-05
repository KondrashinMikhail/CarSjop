package mk.ru.shop.web.responses

import java.util.UUID

data class PaymentWalletInfoResponse(
    val walletId: UUID,
    val userLogin: String
)
