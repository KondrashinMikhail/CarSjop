package mk.ru.carshop.web.requests

data class PasswordChangeRequest(
    val newPassword: String,
    val newPasswordConfirm: String
)