package mk.ru.carshop.web.responses

data class CarAppUserInfoResponse(
    val login: String,
    val mail: String,
    val blocked: Boolean
)
