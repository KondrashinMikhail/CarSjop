package mk.ru.carshop.exceptions

import java.time.LocalDateTime

data class ErrorDetails(
    val timestamp: LocalDateTime,
    val message: String,
    val exceptionClass: String,
    val exceptionProducer: String
)
