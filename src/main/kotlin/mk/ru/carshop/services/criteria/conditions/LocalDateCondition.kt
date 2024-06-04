package mk.ru.carshop.services.criteria.conditions

import java.time.LocalDate
import mk.ru.carshop.enums.CriteriaOperation
import mk.ru.carshop.services.criteria.specifications.LocalDatePredicateSpecification

data class LocalDateCondition(
    override val field: String,
    override val operation: CriteriaOperation,
    override val value: LocalDate,
) : CommonCondition<LocalDate>(
    field = field,
    operation = operation,
    value = value,
    predicateSpecification = LocalDatePredicateSpecification()
)
