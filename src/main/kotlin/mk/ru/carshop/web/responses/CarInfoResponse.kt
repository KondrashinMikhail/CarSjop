package mk.ru.carshop.web.responses

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class CarInfoResponse(
    val id: UUID,
    val manufacturer: String,
    val model: String,
    val registrationDate: LocalDate,
    val deleted: Boolean,
    val price: BigDecimal
)
