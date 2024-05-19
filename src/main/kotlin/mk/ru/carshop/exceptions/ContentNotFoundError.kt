package mk.ru.carshop.exceptions

class ContentNotFoundError(override val message: String) : BaseException(message = message)