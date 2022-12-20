package com.perficient.rulesengine.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DataType{

    TEXT(new String[] {"character varying", "varchar", "character", "char", "text"}, "string"),
    NUMERIC(new String[] {"smallint", "integer", "bigint", "decimal", "numeric","real", "double precision", "serial", "bigserial"}, "number"),
    BOOLEAN(new String[] {"bool", "boolean"}, "boolean");

    private final String[] dbType;
    private final String value;
}
