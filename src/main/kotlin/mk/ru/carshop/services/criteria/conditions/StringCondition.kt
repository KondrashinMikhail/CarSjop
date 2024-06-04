package mk.ru.carshop.services.criteria.conditions

import mk.ru.carshop.enums.CriteriaOperation
import mk.ru.carshop.services.criteria.specifications.StringPredicateSpecification

data class StringCondition(
    override val field: String,
    override val operation: CriteriaOperation,
    override val value: String,
) : CommonCondition<String>(
    field = field,
    operation = operation,
    value = value,
    predicateSpecification = StringPredicateSpecification()
)
