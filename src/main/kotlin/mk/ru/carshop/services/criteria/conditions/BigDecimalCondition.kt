package mk.ru.carshop.services.criteria.conditions

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import mk.ru.carshop.enums.CriteriaOperations
import mk.ru.carshop.services.criteria.specifications.BigDecimalPredicateSpecification

data class BigDecimalCondition(
    override val field: String,
    override val operation: CriteriaOperations,
    override val value: BigDecimal,
    @JsonIgnore
    override val predicateSpecification: BigDecimalPredicateSpecification
) : CommonCondition<BigDecimal>(
    field = field,
    operation = operation,
    value = value,
    predicateSpecification = predicateSpecification
)