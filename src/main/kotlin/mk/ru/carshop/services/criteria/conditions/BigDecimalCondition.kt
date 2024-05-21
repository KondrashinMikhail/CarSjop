package mk.ru.carshop.services.criteria.conditions

import java.math.BigDecimal
import mk.ru.carshop.enums.CriteriaOperations
import mk.ru.carshop.services.criteria.specifications.BigDecimalPredicateSpecification

data class BigDecimalCondition(
    override val field: String,
    override val operation: CriteriaOperations,
    override val value: BigDecimal,
) : CommonCondition<BigDecimal>(
    field = field,
    operation = operation,
    value = value,
    predicateSpecification = BigDecimalPredicateSpecification()
)