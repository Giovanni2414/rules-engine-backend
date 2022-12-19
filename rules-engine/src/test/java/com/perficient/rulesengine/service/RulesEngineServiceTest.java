package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.DynamicData;
import com.perficient.rulesengine.model.NaturalLanguageRule;
import com.perficient.rulesengine.model.Register;
import com.perficient.rulesengine.model.Rule;
import com.perficient.rulesengine.repository.DynamicDBRepository;
import com.perficient.rulesengine.repository.RuleRepository;
import com.perficient.rulesengine.service.impl.RulesEngineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RulesEngineServiceTest {

    private final String INCOME_GREATER_OR_EQUAL_AGE = "{\"expression\":{\">=\":[\"income\",\"age\"]},\"isColumn\":true}";
    private final String AGE_GREATER_OR_EQUAL_10 = "{\"expression\":{\">=\":[\"age\",10]},\"isColumn\":false}";
    private final String IS_INDEPENDENT = "{\"expression\":{\"==\":[\"independent\",true]},\"isColumn\":false}";
    private final String CITY_NOT_BOGOTA = "{\"expression\":{\"!=\":[\"city\",\"Bogota\"]},\"isColumn\":false}";

    private RuleRepository ruleRepository;
    private DynamicDBRepository dynamicDBRepository;
    private RulesEngineService rulesEngineService;

    @BeforeEach
    public void init(){
        ruleRepository = mock(RuleRepository.class);
        dynamicDBRepository = mock(DynamicDBRepository.class);
        rulesEngineService = new RulesEngineServiceImpl(ruleRepository, dynamicDBRepository);
    }

    @Test
    public void testSaveRule(){
        String ruleId = "0a26da1a-6673-4b7e-9b12-84fa6fe0f25a";
        String expressionBody = "exp1 and exp2 and exp3 and exp4";
        String expression1 = INCOME_GREATER_OR_EQUAL_AGE;
        String expression2 = AGE_GREATER_OR_EQUAL_10;
        String expression3 = IS_INDEPENDENT;
        String expression4 = CITY_NOT_BOGOTA;
        String name = "rule1";

        Rule rule = new Rule(UUID.fromString(ruleId), expressionBody, expression1, expression2, expression3, expression4, name);
        String finalExpressionBody = expressionBody.replace("and", "&&").replace("or", "||");
        Rule savedRule = new Rule(UUID.fromString(ruleId), finalExpressionBody, expression1, expression2, expression3, expression4, name);

        when(ruleRepository.save(rule)).thenReturn(savedRule);
        rulesEngineService.saveRule(rule);
        verify(ruleRepository, times(1)).save(rule);
    }

    @Test
    public void testRuleOneExpression(){
        UUID ruleId = UUID.fromString("3524bcb8-ab6a-44cb-828a-49370edbd3b7");
        String expressionBody = "exp1";
        String ruleName = "rule";
        Rule rule = Rule.builder().ruleId(ruleId).ruleName(ruleName)
                .expressionBody(expressionBody)
                .expression1(INCOME_GREATER_OR_EQUAL_AGE)
                .build();

        String jsonArray = getRegistersJsonArray();
        DynamicData registersJsonArray = DynamicData.builder().data(jsonArray).id(4).build();
        List<Register> expectedPositiveRegisters = new ArrayList<>();
        expectedPositiveRegisters.add(new Register("1"));
        expectedPositiveRegisters.add(new Register("2"));
        expectedPositiveRegisters.add(new Register("3"));
        expectedPositiveRegisters.add(new Register("4"));

        when(ruleRepository.findById(ruleId)).thenReturn(Optional.ofNullable(rule));
        when(dynamicDBRepository.getDataAsJson()).thenReturn(registersJsonArray);

        List<Register> positiveRegisters = rulesEngineService.evaluateRule(ruleId);
        verify(ruleRepository, times(1)).findById(ruleId);
        verify(dynamicDBRepository, times(1)).getDataAsJson();
        assertEquals(expectedPositiveRegisters, positiveRegisters);
    }

    @Test
    public void testRuleTwoExpressions(){
        UUID ruleId = UUID.fromString("3524bcb8-ab6a-44cb-828a-49370edbd3b7");
        String expressionBody = "exp1 and exp2";
        String ruleName = "rule";
        Rule rule = Rule.builder().ruleId(ruleId).ruleName(ruleName)
                .expressionBody(expressionBody)
                .expression1(INCOME_GREATER_OR_EQUAL_AGE)
                .expression2(AGE_GREATER_OR_EQUAL_10)
                .build();

        String jsonArray = getRegistersJsonArray();
        DynamicData registersJsonArray = DynamicData.builder().data(jsonArray).id(4).build();
        List<Register> expectedPositiveRegisters = new ArrayList<>();
        expectedPositiveRegisters.add(new Register("1"));
        expectedPositiveRegisters.add(new Register("2"));
        expectedPositiveRegisters.add(new Register("3"));
        expectedPositiveRegisters.add(new Register("4"));

        when(ruleRepository.findById(ruleId)).thenReturn(Optional.ofNullable(rule));
        when(dynamicDBRepository.getDataAsJson()).thenReturn(registersJsonArray);

        List<Register> positiveRegisters = rulesEngineService.evaluateRule(ruleId);
        verify(ruleRepository, times(1)).findById(ruleId);
        verify(dynamicDBRepository, times(1)).getDataAsJson();
        assertEquals(expectedPositiveRegisters, positiveRegisters);
    }

    @Test
    public void testRuleThreeExpressions(){
        UUID ruleId = UUID.fromString("3524bcb8-ab6a-44cb-828a-49370edbd3b7");
        String expressionBody = "exp1 and exp2 and exp3";
        String ruleName = "rule";
        Rule rule = Rule.builder().ruleId(ruleId).ruleName(ruleName)
                .expressionBody(expressionBody)
                .expression1(INCOME_GREATER_OR_EQUAL_AGE)
                .expression2(AGE_GREATER_OR_EQUAL_10)
                .expression3(IS_INDEPENDENT)
                .build();

        String jsonArray = getRegistersJsonArray();
        DynamicData registersJsonArray = DynamicData.builder().data(jsonArray).id(4).build();
        List<Register> expectedPositiveRegisters = new ArrayList<>();
        expectedPositiveRegisters.add(new Register("1"));
        expectedPositiveRegisters.add(new Register("3"));
        expectedPositiveRegisters.add(new Register("4"));

        when(ruleRepository.findById(ruleId)).thenReturn(Optional.ofNullable(rule));
        when(dynamicDBRepository.getDataAsJson()).thenReturn(registersJsonArray);

        List<Register> positiveRegisters = rulesEngineService.evaluateRule(ruleId);
        verify(ruleRepository, times(1)).findById(ruleId);
        verify(dynamicDBRepository, times(1)).getDataAsJson();
        assertEquals(expectedPositiveRegisters, positiveRegisters);
    }

    @Test
    public void testRuleFourExpressions(){
        UUID ruleId = UUID.fromString("3524bcb8-ab6a-44cb-828a-49370edbd3b7");
        String expressionBody = "exp1 and exp2 and exp3 and exp4";
        String ruleName = "rule";
        Rule rule = Rule.builder().ruleId(ruleId).ruleName(ruleName)
                .expressionBody(expressionBody)
                .expression1(INCOME_GREATER_OR_EQUAL_AGE)
                .expression2(AGE_GREATER_OR_EQUAL_10)
                .expression3(IS_INDEPENDENT)
                .expression4(CITY_NOT_BOGOTA)
                .build();

        String jsonArray = getRegistersJsonArray();
        DynamicData registersJsonArray = DynamicData.builder().data(jsonArray).id(4).build();
        List<Register> expectedPositiveRegisters = new ArrayList<>();
        expectedPositiveRegisters.add(new Register("1"));
        expectedPositiveRegisters.add(new Register("4"));

        when(ruleRepository.findById(ruleId)).thenReturn(Optional.ofNullable(rule));
        when(dynamicDBRepository.getDataAsJson()).thenReturn(registersJsonArray);

        List<Register> positiveRegisters = rulesEngineService.evaluateRule(ruleId);
        verify(ruleRepository, times(1)).findById(ruleId);
        verify(dynamicDBRepository, times(1)).getDataAsJson();
        assertEquals(expectedPositiveRegisters, positiveRegisters);
    }

    private String getRegistersJsonArray(){
        return
                "[{\"id\":\"1\",\"age\":10,\"city\":\"Cali\",\"independent\":true,\"income\":10}," + "\n" +
                " {\"id\":\"2\",\"age\":20,\"city\":\"Cali\",\"independent\":false,\"income\":100}, " + "\n" +
                " {\"id\":\"3\",\"age\":30,\"city\":\"Bogota\",\"independent\":true,\"income\":1000}, " + "\n" +
                " {\"id\":\"4\",\"age\":25,\"city\":\"Cali\",\"independent\":true,\"income\":2000}]";
    }

    @Test
    public void testGetRules(){
        List<Rule> rules = new ArrayList<>();
        when(ruleRepository.findAll()).thenReturn(rules);
        List<NaturalLanguageRule> naturalLanguageRules = rulesEngineService.getRules();
        verify(ruleRepository, times(1)).findAll();
    }
}
