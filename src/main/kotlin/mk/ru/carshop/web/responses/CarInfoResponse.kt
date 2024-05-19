package mk.ru.carshop.web.responses

import java.time.LocalDateTime
import java.util.*

data class CarInfoResponse(
    val id: UUID,
    val manufacturer: String,
    val model: String,
    val creationDateTime: LocalDateTime,
    val isDeleted: Boolean
)
