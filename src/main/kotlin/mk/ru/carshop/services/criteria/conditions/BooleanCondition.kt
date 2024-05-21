package mk.ru.carshop.services.criteria.conditions

import mk.ru.carshop.enums.CriteriaOperations
import mk.ru.carshop.services.criteria.specifications.BooleanPredicateSpecification

data class BooleanCondition(
    override val field: String,
    override val operation: CriteriaOperations,
    override val value: Boolean
) : CommonCondition<Boolean>(
    field = field,
    operation = operation,
    value = value,
    predicateSpecification = BooleanPredicateSpecification()
)
