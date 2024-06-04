package mk.ru.carshop.web.requests

data class AuthenticationRequest(
    val login: String,
    val password: String
)
