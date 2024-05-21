package mk.ru.carshop.exceptions

import org.springframework.http.HttpStatus

class ContentNotFoundError(override val message: String) :
    BaseException(message = message, status = HttpStatus.NO_CONTENT)