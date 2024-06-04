package mk.ru.carshop.web.requests

import java.math.BigDecimal
import java.util.UUID

data class CarUpdateRequest(
    val id: UUID,
    val manufacturer: String?,
    val model: String?,
    val price: BigDecimal?
)
