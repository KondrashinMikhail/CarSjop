package mk.ru.carshop.exceptions

class ValidationException(override val message: String) : BaseException(message = message)