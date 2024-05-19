package mk.ru.carshop.web.requests

data class CreateCarRequest(
    val manufacturer: String,
    val model: String
)