package com.perficient.rulesengine.service.impl;

import com.perficient.rulesengine.constant.ExpressionAlias;
import com.perficient.rulesengine.model.DynamicData;
import com.perficient.rulesengine.model.NaturalLanguageRule;
import com.perficient.rulesengine.model.Register;
import com.perficient.rulesengine.model.Rule;
import com.perficient.rulesengine.repository.DynamicDBRepository;
import com.perficient.rulesengine.repository.RuleRepository;
import com.perficient.rulesengine.service.RulesEngineService;
import io.github.jamsesso.jsonlogic.JsonLogic;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class RulesEngineServiceImpl implements RulesEngineService {

    private final RuleRepository ruleRepository;
    private final DynamicDBRepository dynamicDBRepository;

    @Override
    public Rule saveRule(Rule rule) {
        rule.setExpressionBody(rule.getExpressionBody().replace("and", "&&"));
        rule.setExpressionBody(rule.getExpressionBody().replace("or", "||"));
        return ruleRepository.save(rule);
    }

    @Override
    public List<Register> evaluateRule(UUID ruleId) {
        Rule rule = ruleRepository.findById(ruleId).orElse(null);
        List<DynamicData> registers = getRegisters();
        List<Register> positiveRegisters = new ArrayList<>();

        for (DynamicData register : registers) {
            Map<String, Boolean> context = new java.util.HashMap<>();

            context.put(ExpressionAlias.EXPRESSION_1.getAlias(), evaluateExpression(rule.getExpression1(), register));
            context.put(ExpressionAlias.EXPRESSION_2.getAlias(), evaluateExpression(rule.getExpression2(), register));
            context.put(ExpressionAlias.EXPRESSION_3.getAlias(), evaluateExpression(rule.getExpression3(), register));
            context.put(ExpressionAlias.EXPRESSION_4.getAlias(), evaluateExpression(rule.getExpression4(), register));

            boolean isTrue = (boolean) MVEL.eval(rule.getExpressionBody(), context);
            if (isTrue) {
                JSONObject registerDataJson = new JSONObject(register.getData());
                positiveRegisters.add(Register.builder().registerId(registerDataJson.getString("id")).build());
            }
        }

        return positiveRegisters;
    }

    @SneakyThrows
    private Boolean evaluateExpression(String expression, DynamicData register) {
        if (expression == null) {
            return false;
        }
        //Extract expression data as JSON
        JSONObject jsonObject = new JSONObject(expression);
        JSONObject expressionJson = jsonObject.getJSONObject("expression");
        String operator = expressionJson.keys().next();
        JSONArray values = expressionJson.getJSONArray(operator);

        //Extract register data
        JSONObject registerDataJson = new JSONObject(register.getData());
        values.put(0, registerDataJson.get(values.get(0).toString()));

        //Replace register data in expression
        boolean isColumn = jsonObject.getBoolean("isColumn");
        if (isColumn) {
            values.put(1, registerDataJson.get(values.get(1).toString()));
        }
        expressionJson.put(operator, values);

        //Evaluate expression
        JsonLogic jsonLogic = new JsonLogic();
        return (boolean) jsonLogic.apply(expressionJson.toString(), null);
    }

    private List<DynamicData> getRegisters() {
        JSONArray registersJsonArray = new JSONArray(dynamicDBRepository.getDataAsJson().getData());
        List<DynamicData> registers = new ArrayList<>();
        for (Object registerJsonArray : registersJsonArray) {
            registers.add(DynamicData.builder().data(registerJsonArray.toString()).build());
        }
        return registers;
    }

    @Override
    public List<NaturalLanguageRule> getRules() {
        return transformRulesToNarutalLanguage();
    }

    private List<NaturalLanguageRule> transformRulesToNarutalLanguage(){
        List<Rule> rules = StreamSupport.stream(ruleRepository.findAll().spliterator(), false).collect(Collectors.toList());
        List<NaturalLanguageRule> naturalLanguageRules = new ArrayList<>();

        for (Rule rule:rules) {
            String naturalLanguajeBody =rule.getExpressionBody();
            naturalLanguajeBody = naturalLanguajeBody.replace(ExpressionAlias.EXPRESSION_1.getAlias(), transformExpressionToNaturalLanguage(rule.getExpression1()));
            naturalLanguajeBody = naturalLanguajeBody.replace(ExpressionAlias.EXPRESSION_2.getAlias(), transformExpressionToNaturalLanguage(rule.getExpression2()));
            naturalLanguajeBody = naturalLanguajeBody.replace(ExpressionAlias.EXPRESSION_3.getAlias(), transformExpressionToNaturalLanguage(rule.getExpression3()));
            naturalLanguajeBody = naturalLanguajeBody.replace(ExpressionAlias.EXPRESSION_4.getAlias(), transformExpressionToNaturalLanguage(rule.getExpression4()));
            naturalLanguajeBody = naturalLanguajeBody.replace("&&", "AND");
            naturalLanguajeBody = naturalLanguajeBody.replace("||", "OR");

            NaturalLanguageRule currentRule = new NaturalLanguageRule(rule.getRuleId().toString(), "x", naturalLanguajeBody);
            naturalLanguageRules.add(currentRule);
        }
        return naturalLanguageRules;
    }

    private String transformExpressionToNaturalLanguage(String  expression){
        String output = "";
        if(expression != null){
            JSONObject jsonObject = new JSONObject(expression);
            JSONObject expressionJson = jsonObject.getJSONObject("expression");
            String operator = expressionJson.keys().next();
            JSONArray values = expressionJson.getJSONArray(operator);

            String operand1 = values.get(0).toString();
            String operand2 = values.get(1).toString();

            output = operand1 + " " + operator + " " + operand2;
        }
        return output;
    }

}
