package mk.ru.shop.web.responses.payment

import java.util.UUID

data class PaymentWalletInfoResponse(
    val walletId: UUID,
    val userLogin: String
)
