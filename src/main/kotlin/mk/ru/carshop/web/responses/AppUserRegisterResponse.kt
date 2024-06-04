package mk.ru.carshop.web.responses

import java.util.UUID

data class AppUserRegisterResponse(
    val id: UUID,
    val login: String,
)
