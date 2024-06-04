package mk.ru.carshop.web.requests

import java.math.BigDecimal

data class CarCreateRequest(
    val manufacturer: String,
    val model: String,
    val price: BigDecimal
)