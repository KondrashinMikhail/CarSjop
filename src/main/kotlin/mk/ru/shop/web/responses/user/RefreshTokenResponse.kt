package mk.ru.shop.web.responses.user

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)