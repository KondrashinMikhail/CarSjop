package mk.ru.shop.exceptions

class ValidationException(override val message: String) : BaseException(message = message)