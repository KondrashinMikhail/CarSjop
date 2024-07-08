package mk.ru.shop.web.responses.product

data class ProductAppUserInfoResponse(
    val login: String,
    val mail: String,
    val blocked: Boolean
)
