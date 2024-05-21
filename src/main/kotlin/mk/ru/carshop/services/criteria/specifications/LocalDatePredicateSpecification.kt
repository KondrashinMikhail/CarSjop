package mk.ru.carshop.services.criteria.specifications

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import java.time.LocalDate
import java.time.LocalDateTime

class LocalDatePredicateSpecification : PredicateSpecification<LocalDate> {
    override fun equalPredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.equal(criteriaBuilder.function("date", LocalDateTime::class.java, expression), value)
    }

    override fun notEqualPredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.notEqual(expression, value)
    }

    override fun greaterThanPredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.greaterThan(expression, value);
    }

    override fun greaterThanOrEqualPredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.greaterThanOrEqualTo(expression, value)
    }

    override fun lessThanPredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.lessThan(expression, value)
    }

    override fun lessThanOrEqualPredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.lessThanOrEqualTo(expression, value)
    }

    override fun likePredicate(
        expression: Expression<LocalDate>,
        value: LocalDate,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.between(expression, value.minusDays(1), value.plusDays(1))
    }
}