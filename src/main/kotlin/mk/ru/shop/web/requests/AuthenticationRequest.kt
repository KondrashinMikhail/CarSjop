package mk.ru.shop.web.requests

data class AuthenticationRequest(
    val login: String,
    val password: String
)
