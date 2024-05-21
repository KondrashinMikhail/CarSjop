package mk.ru.carshop.enums

import com.fasterxml.jackson.annotation.JsonAlias

enum class CriteriaOperations {
    @JsonAlias("=", "equals")
    EQUALS,

    @JsonAlias("!=", "not_equals")
    NOT_EQUALS,

    @JsonAlias(">", "greater_than")
    GREATER_THAN,

    @JsonAlias(">=", "greater_than_or_equal")
    GREATER_THAN_OR_EQUAL,

    @JsonAlias("<", "less_than")
    LESS_THAN,

    @JsonAlias("<=", "less_than_or_equal")
    LESS_THAN_OR_EQUAL,

    @JsonAlias("~", "like")
    LIKE
}
