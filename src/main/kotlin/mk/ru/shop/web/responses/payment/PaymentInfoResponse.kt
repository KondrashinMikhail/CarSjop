package mk.ru.shop.web.responses.payment

data class PaymentInfoResponse(
    val product: PaymentProductInfoResponse,
    val sender: PaymentWalletInfoResponse,
    val recipient: PaymentWalletInfoResponse,
    val transaction: PaymentTransactionInfoResponse
)
