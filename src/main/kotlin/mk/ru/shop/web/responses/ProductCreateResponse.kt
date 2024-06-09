package mk.ru.shop.web.responses

import java.time.LocalDate
import java.util.UUID

data class ProductCreateResponse(
    val id: UUID,
    val registrationDate: LocalDate
)
