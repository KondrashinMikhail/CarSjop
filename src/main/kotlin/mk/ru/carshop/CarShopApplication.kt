package mk.ru.carshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class CarShopApplication

fun main(args: Array<String>) {
	runApplication<CarShopApplication>(*args)
}
