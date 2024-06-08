package mk.ru.carshop.web.responses

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)