package mk.ru.carshop.services.criteria.conditions

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import mk.ru.carshop.enums.CriteriaOperations
import mk.ru.carshop.services.criteria.specifications.LocalDatePredicateSpecification

data class LocalDateCondition(
    override val field: String,
    override val operation: CriteriaOperations,
    override val value: LocalDate,
    @JsonIgnore
    override val predicateSpecification: LocalDatePredicateSpecification
) : CommonCondition<LocalDate>(
    field = field,
    operation = operation,
    value = value,
    predicateSpecification = predicateSpecification
)
