package mk.ru.carshop.web.requests

import java.math.BigDecimal

data class CreateCarRequest(
    val manufacturer: String,
    val model: String,
    val price: BigDecimal
)