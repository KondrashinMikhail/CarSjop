package mk.ru.carshop.services.criteria.specifications

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import java.math.BigDecimal

class BigDecimalPredicateSpecification : PredicateSpecification<BigDecimal> {
    override fun equalPredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.equal(expression, value)
    }

    override fun notEqualPredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.notEqual(expression, value)
    }

    override fun greaterThanPredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.greaterThan(expression, value)
    }

    override fun greaterThanOrEqualPredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.greaterThanOrEqualTo(expression, value)
    }

    override fun lessThanPredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.lessThan(expression, value)
    }

    override fun lessThanOrEqualPredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.lessThanOrEqualTo(expression, value)
    }

    override fun likePredicate(
        expression: Expression<BigDecimal>,
        value: BigDecimal,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        return criteriaBuilder.between(
            expression,
            value.multiply(BigDecimal.valueOf(0.9)),
            value.multiply(BigDecimal.valueOf(1.1))
        )
    }
}