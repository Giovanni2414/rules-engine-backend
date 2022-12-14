package com.perficient.rulesengine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleDTO {
    private String expressionBody;
    private String expression1;
    private String expression2;
    private String expression3;
    private String expression4;
}
