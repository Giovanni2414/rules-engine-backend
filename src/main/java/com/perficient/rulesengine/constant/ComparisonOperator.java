package com.perficient.rulesengine.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ComparisonOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    LESS_THAN ("<"),
    LIKE ("LIKE"),
    NOT_LIKE ( "NOT LIKE");
    public final String operator;
}
