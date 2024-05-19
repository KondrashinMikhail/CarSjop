package mk.ru.carshop.web.responses

import java.time.LocalDateTime
import java.util.UUID

data class CreateCarResponse(
    val id: UUID,
    val creationDateTime: LocalDateTime
)
