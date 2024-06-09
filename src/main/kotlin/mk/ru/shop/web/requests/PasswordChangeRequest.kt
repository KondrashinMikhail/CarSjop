package mk.ru.shop.web.requests

data class PasswordChangeRequest(
    val newPassword: String,
    val newPasswordConfirm: String
)