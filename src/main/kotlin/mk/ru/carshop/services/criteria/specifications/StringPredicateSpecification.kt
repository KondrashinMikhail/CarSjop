package mk.ru.carshop.services.criteria.specifications

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate

class StringPredicateSpecification : PredicateSpecification<String> {
    override fun equalPredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.equal(expression, value)
    }

    override fun notEqualPredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.notEqual(expression, value)
    }

    override fun greaterThanPredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.greaterThan(expression, value);
    }

    override fun greaterThanOrEqualPredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.greaterThanOrEqualTo(expression, value)
    }

    override fun lessThanPredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.lessThan(expression, value)
    }

    override fun lessThanOrEqualPredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.lessThanOrEqualTo(expression, value)
    }

    override fun likePredicate(
        expression: Expression<String>,
        value: String,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.like(expression, "%$value%")
    }
}