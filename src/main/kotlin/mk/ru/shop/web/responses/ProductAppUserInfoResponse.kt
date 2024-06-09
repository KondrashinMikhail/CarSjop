package mk.ru.shop.web.responses

data class ProductAppUserInfoResponse(
    val login: String,
    val mail: String,
    val blocked: Boolean
)
