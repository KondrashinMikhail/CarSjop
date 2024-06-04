package mk.ru.carshop.web.responses

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
