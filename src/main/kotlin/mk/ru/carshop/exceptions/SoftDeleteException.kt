package mk.ru.carshop.exceptions

import org.springframework.http.HttpStatus.NO_CONTENT

class SoftDeleteException(override val message: String) : BaseException(message = message, status = NO_CONTENT)