package mk.ru.carshop.web.responses

import java.time.LocalDate
import java.util.UUID

data class CarCreateResponse(
    val id: UUID,
    val registrationDate: LocalDate
)
