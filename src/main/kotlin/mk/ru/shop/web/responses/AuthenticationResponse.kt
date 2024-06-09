package mk.ru.shop.web.responses

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
