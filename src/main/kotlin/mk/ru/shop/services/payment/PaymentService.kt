package mk.ru.shop.services.payment

import java.util.UUID
import mk.ru.shop.web.responses.PaymentInfoResponse

interface PaymentService {
    fun buyProduct(productId: UUID): PaymentInfoResponse
}