package mk.ru.carshop.web.responses

import java.time.LocalDate
import java.util.UUID

data class CreateCarResponse(
    val id: UUID,
    val registrationDate: LocalDate
)
