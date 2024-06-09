package mk.ru.shop.web.responses

import java.time.LocalDate

data class AppUserRegisterResponse(
    val login: String,
    val registrationDate: LocalDate,
)
