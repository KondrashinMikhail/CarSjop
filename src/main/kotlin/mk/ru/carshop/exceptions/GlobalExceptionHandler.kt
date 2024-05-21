package mk.ru.carshop.exceptions

import java.time.LocalDateTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private final val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<ErrorDetails> {
        log.error(e.message)
        return ResponseEntity.status(e.status).body(
            ErrorDetails(
                timestamp = LocalDateTime.now(),
                message = e.message,
                exceptionProducer = e.stackTrace[0].className.toString(),
                exceptionClass = e.javaClass.name
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorDetails> {
        val message: String = e.message ?: "Unexpected error"
        log.error(message)
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
            ErrorDetails(
                timestamp = LocalDateTime.now(),
                message = message,
                exceptionProducer = e.stackTrace[0].className.toString(),
                exceptionClass = e.javaClass.name
            )
        )
    }
}