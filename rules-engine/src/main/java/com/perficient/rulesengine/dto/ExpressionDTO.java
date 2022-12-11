package com.perficient.rulesengine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.perficient.rulesengine.constant.ComparisonOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpressionDTO extends Operand {
    ColumnDTO leftOperand;
    ComparisonOperator operator;

    ExpressionOperandDTO rightOperand;

}
