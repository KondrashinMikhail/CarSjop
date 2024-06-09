package mk.ru.shop.web.requests

import java.math.BigDecimal

data class ProductCreateRequest(
    val name: String,
    val description: String?,
    val price: BigDecimal
)