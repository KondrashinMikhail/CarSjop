package mk.ru.shop.services.product

import java.util.UUID
import mk.ru.shop.services.criteria.conditions.CommonCondition
import mk.ru.shop.web.requests.ProductCreateRequest
import mk.ru.shop.web.requests.ProductUpdateRequest
import mk.ru.shop.web.responses.ProductInfoResponse
import mk.ru.shop.web.responses.ProductCreateResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductService {
    fun searchProducts(conditions: List<CommonCondition<Any>>?, pageable: Pageable?): Page<ProductInfoResponse>
    fun findProducts(byOwner: Boolean?, showDeleted: Boolean?, pageable: Pageable?): Page<ProductInfoResponse>
    fun findById(id: UUID): ProductInfoResponse
    fun createProduct(productCreateRequest: ProductCreateRequest): ProductCreateResponse
    fun updateProduct(productUpdateRequest: ProductUpdateRequest): ProductInfoResponse
    fun deleteProduct(id: UUID)
    fun restoreProduct(id: UUID)
}