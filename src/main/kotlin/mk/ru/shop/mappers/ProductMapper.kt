package mk.ru.shop.mappers

import mk.ru.shop.persistence.entities.Product
import mk.ru.shop.web.requests.ProductCreateRequest
import mk.ru.shop.web.responses.ProductCreateResponse
import mk.ru.shop.web.responses.ProductInfoResponse
import org.springframework.stereotype.Component

@Component
class ProductMapper(
    private val appUserMapper: AppUserMapper
) {
    fun toCreateResponse(product: Product): ProductCreateResponse = ProductCreateResponse(
        id = product.id!!,
        registrationDate = product.registrationDate!!
    )

    fun toInfoResponse(product: Product): ProductInfoResponse = ProductInfoResponse(
        id = product.id!!,
        name = product.name!!,
        description = product.description,
        registrationDate = product.registrationDate!!,
        deleted = product.deleted!!,
        price = product.price!!,
        owner = appUserMapper.toInfoResponse(product.owner!!)
    )

    fun toEntity(product: ProductCreateRequest): Product = Product(
        name = product.name,
        description = product.description,
        price = product.price
    )
}