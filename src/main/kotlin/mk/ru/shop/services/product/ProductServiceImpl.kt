package mk.ru.shop.services.product

import jakarta.persistence.criteria.Predicate
import java.util.UUID
import mk.ru.shop.exceptions.ContentNotFoundError
import mk.ru.shop.exceptions.SoftDeletionException
import mk.ru.shop.mappers.ProductMapper
import mk.ru.shop.persistence.entities.Product
import mk.ru.shop.persistence.repositories.AppUserRepository
import mk.ru.shop.persistence.repositories.ProductRepository
import mk.ru.shop.services.criteria.conditions.CommonCondition
import mk.ru.shop.utils.AppUserInfo
import mk.ru.shop.web.requests.ProductCreateRequest
import mk.ru.shop.web.requests.ProductUpdateRequest
import mk.ru.shop.web.responses.ProductCreateResponse
import mk.ru.shop.web.responses.ProductInfoResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val appUserRepository: AppUserRepository,
    private val productMapper: ProductMapper,
) : ProductService {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun searchProducts(
        conditions: List<CommonCondition<Any>>?,
        pageable: Pageable?
    ): Page<ProductInfoResponse> {
        val specification: Specification<Product> = Specification<Product> { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()
            conditions?.forEach { condition ->
                predicates.add(
                    condition.operation.getPredicate(
                        predicateSpecification = condition.predicateSpecification,
                        expression = root.get(condition.field),
                        value = condition.value,
                        criteriaBuilder = criteriaBuilder
                    )
                )
            }
            criteriaBuilder.and(* predicates.toTypedArray())
        }
        val products: Page<Product> = productRepository.findAll(specification, pageable ?: Pageable.unpaged())
        log.info("Found ${products.totalElements} of products ${conditions?.let { "with ${it.size} of" } ?: "without"} conditions")
        return products.map { productMapper.toInfoResponse(it) }
    }

    override fun findProducts(
        byOwner: Boolean?,
        showDeleted: Boolean?,
        pageable: Pageable?
    ): Page<ProductInfoResponse> {
        val login: String = AppUserInfo.getAuthenticatedLogin()
        val finalPageable = pageable ?: Pageable.unpaged()

        val products: Page<Product> = when (showDeleted) {
            null, false -> when (byOwner) {
                null, false -> productRepository.findByDeletedFalse(finalPageable)
                true -> productRepository.findByDeletedFalseAndOwnerLogin(login = login, pageable = finalPageable)
            }

            true -> when (byOwner) {
                null, false -> productRepository.findAll(finalPageable)
                true -> productRepository.findByOwnerLogin(login = login, pageable = finalPageable)
            }
        }

        log.info("Found ${products.totalElements} of available ${showDeleted ?: "and deleted"} products ${byOwner ?: "and by owner '$login'"}")
        return products.map { productMapper.toInfoResponse(it) }
    }

    override fun findById(id: UUID): ProductInfoResponse {
        val product: Product = findEntityById(id)
        log.info("Found product with id - $id")
        return productMapper.toInfoResponse(product)
    }

    override fun createProduct(productCreateRequest: ProductCreateRequest): ProductCreateResponse {
        val product: Product = productMapper.toEntity(productCreateRequest)

        val authenticatedLogin: String = AppUserInfo.getAuthenticatedLogin()
        product.owner = appUserRepository.findById(authenticatedLogin)
            .orElseThrow { ContentNotFoundError("User with login - $authenticatedLogin not found") }

        val savedProduct: Product = productRepository.save(product)
        log.info("Created product with id - ${savedProduct.id}")
        return productMapper.toCreateResponse(savedProduct)
    }

    override fun updateProduct(productUpdateRequest: ProductUpdateRequest): ProductInfoResponse {
        val product: Product = findEntityById(id = productUpdateRequest.id, deletionCheck = true)
        AppUserInfo.checkAccessAllowed(product.owner?.login!!)

        productUpdateRequest.name?.let { product.name = it }
        productUpdateRequest.description?.let { product.description = it }
        productUpdateRequest.price?.let { product.price = it }

        val updatedProduct: Product = productRepository.save(product)
        log.info("Updated product with id - ${updatedProduct.id}")
        return productMapper.toInfoResponse(updatedProduct)
    }

    override fun deleteProduct(id: UUID) {
        val product: Product = findEntityById(id = id, deletionCheck = true)
        product.deleted = true
        productRepository.save(product)
        log.info("Deleted product with id - $id")
    }

    override fun restoreProduct(id: UUID) {
        val product: Product = findEntityById(id)
        when (product.deleted!!) {
            true -> product.deleted = false
            false -> throw SoftDeletionException("Product with id - $id is not deleted")
        }
        productRepository.save(product)
        log.info("Restored product with id - $id")
    }

    private fun findEntityById(id: UUID, deletionCheck: Boolean = false): Product {
        val product: Product =
            productRepository.findById(id).orElseThrow { ContentNotFoundError("Product with id - $id not found") }
        if (deletionCheck && product.deleted!!)
            throw SoftDeletionException("Product with id - $id not found")
        return product
    }
}