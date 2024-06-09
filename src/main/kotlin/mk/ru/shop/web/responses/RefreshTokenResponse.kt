package mk.ru.shop.web.responses

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)