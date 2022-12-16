package com.perficient.rulesengine.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExpressionAlias {

    EXPRESSION_1("exp1"),
    EXPRESSION_2("exp2"),
    EXPRESSION_3("exp3"),
    EXPRESSION_4("exp4");

    private final String alias;
}
