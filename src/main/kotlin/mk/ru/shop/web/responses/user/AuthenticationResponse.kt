package mk.ru.shop.web.responses.user

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
