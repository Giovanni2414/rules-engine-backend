package com.perficient.rulesengine.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LogicalOperator {
    AND("and", "&&"),
    OR("or", "||");

    private final String naturalLanguage;
    private final String mvelValue;

}
