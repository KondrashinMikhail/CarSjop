package mk.ru.shop.web.controllers

import java.util.UUID
import mk.ru.shop.services.criteria.conditions.CommonCondition
import mk.ru.shop.services.product.ProductService
import mk.ru.shop.web.requests.ProductCreateRequest
import mk.ru.shop.web.requests.ProductUpdateRequest
import mk.ru.shop.web.responses.ProductCreateResponse
import mk.ru.shop.web.responses.ProductInfoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController(private val productService: ProductService) {
    @PostMapping("/search")
    fun search(
        @RequestBody(required = false) conditions: List<CommonCondition<Any>>?,
        @RequestParam(required = false) pageable: Pageable?
    ): ResponseEntity<Page<ProductInfoResponse>> =
        ResponseEntity.ok(
            productService.searchProducts(
                conditions = conditions,
                pageable = pageable
            )
        )

    @GetMapping
    fun find(
        @RequestParam(required = false) byOwner: Boolean? = true,
        @RequestParam(required = false) showDeleted: Boolean? = false,
        @RequestParam(required = false) pageable: Pageable?
    ): ResponseEntity<Page<ProductInfoResponse>> =
        ResponseEntity.ok(
            productService.findProducts(
                byOwner = byOwner,
                showDeleted = showDeleted,
                pageable = pageable
            )
        )

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<ProductInfoResponse> =
        ResponseEntity.ok(productService.findById(id))

    @PostMapping
    fun create(@RequestBody productCreateRequest: ProductCreateRequest): ResponseEntity<ProductCreateResponse> =
        ResponseEntity.ok(productService.createProduct(productCreateRequest))

    @PatchMapping
    fun update(@RequestBody productUpdateRequest: ProductUpdateRequest): ResponseEntity<ProductInfoResponse> =
        ResponseEntity.ok(productService.updateProduct(productUpdateRequest))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> =
        ResponseEntity.ok(productService.deleteProduct(id))

    @PatchMapping("/{id}/restore")
    fun restore(@PathVariable id: UUID): ResponseEntity<Unit> =
        ResponseEntity.ok(productService.restoreProduct(id))
}