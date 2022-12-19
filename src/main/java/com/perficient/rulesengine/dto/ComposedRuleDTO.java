package com.perficient.rulesengine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.perficient.rulesengine.constant.LogicalOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComposedRuleDTO extends Operand{
    Operand leftOperand;
    LogicalOperator operator;
    Operand rightOperand;
}
