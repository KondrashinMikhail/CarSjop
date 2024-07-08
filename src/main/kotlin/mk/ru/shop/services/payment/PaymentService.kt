package mk.ru.shop.services.payment

import java.util.UUID
import mk.ru.shop.web.responses.payment.PaymentInfoResponse

interface PaymentService {
    fun buyProduct(productId: UUID): PaymentInfoResponse
}