package mk.ru.carshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarShopApplication

fun main(args: Array<String>) {
	runApplication<CarShopApplication>(*args)
}
