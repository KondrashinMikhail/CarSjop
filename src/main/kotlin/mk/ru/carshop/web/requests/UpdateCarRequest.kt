package mk.ru.carshop.web.requests

import java.util.UUID

data class UpdateCarRequest(
    val id: UUID,
    val manufacturer: String?,
    val model: String?
)
