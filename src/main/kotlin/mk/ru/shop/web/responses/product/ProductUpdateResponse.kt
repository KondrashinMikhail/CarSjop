package mk.ru.shop.web.responses.product

import java.math.BigDecimal
import java.util.UUID

data class ProductUpdateResponse(
    val id: UUID,
    val name: String?,
    val description: String?,
    val actualPrice: BigDecimal?,
)
