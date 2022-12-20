package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.dto.NaturalLanguageRuleDTO;
import com.perficient.rulesengine.dto.RuleDTO;
import com.perficient.rulesengine.mapper.*;
import com.perficient.rulesengine.model.NaturalLanguageRule;
import com.perficient.rulesengine.model.Register;
import com.perficient.rulesengine.service.RulesEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RulesEngineControllerTest {

    private RulesEngineController rulesEngineController;

    private RulesEngineService rulesEngineService;

    private RuleMapper ruleMapper;

    private RegisterMapper registerMapper;

    private NaturalLanguajeRuleMapper naturalLanguajeRuleMapper;

    @BeforeEach
    public void init(){
        rulesEngineService = mock(RulesEngineService.class);
        ruleMapper = new RuleMapperImpl();
        registerMapper = new RegisterMapperImpl();
        naturalLanguajeRuleMapper = new NaturalLanguajeRuleMapperImpl();
        rulesEngineController = new RulesEngineController(rulesEngineService, ruleMapper, registerMapper, naturalLanguajeRuleMapper);
    }

    @Test
    public void saveRuleTest(){
        RuleDTO ruleDTO = new RuleDTO("exp1 and exp2 and exp3 and exp4","{\"expression\":{\">=\":[\"edad\",\"income\"]},\"isColumn\":true}",
                "{\"expression\":{\">=\":[\"edad\",10]},\"isColumn\":false}","{\"expression\":{\"==\":[\"isasociate\",true]},\"isColumn\":false}",
                "{\"expression\":{\"==\":[\"ciudad\",\"Cali\"]},\"isColumn\":false}", "rule1");
        rulesEngineController.saveRule(ruleDTO);
        verify(rulesEngineService, times(1)).saveRule(ruleMapper.fromDTO(ruleDTO));
    }

    @Test
    public void evaluateRule(){
        UUID ruleId = UUID.randomUUID();
        List<Register> registers = new ArrayList<>();
        when(rulesEngineService.evaluateRule(ruleId)).thenReturn(registers);
        rulesEngineController.evaluateRule(ruleId);
        verify(rulesEngineService, times(1)).evaluateRule(ruleId);
    }

    @Test
    public void getRulesTest(){

        String ruleId = "17e586fa-11ce-48b6-82da-37e12bd3801c";
        String ruleName = "rule1";
        String ruleNaturalLanguage = "income >= age AND age >= 10 AND independent == true AND city != Bogota";

        List<NaturalLanguageRule> naturalLanguageRules = new ArrayList<>();
        NaturalLanguageRule rule = new NaturalLanguageRule(ruleId, ruleName, ruleNaturalLanguage);
        naturalLanguageRules.add(rule);

        List<NaturalLanguageRuleDTO> naturalLanguageRulesDTO = new ArrayList<>();
        NaturalLanguageRuleDTO ruleDTO = new NaturalLanguageRuleDTO(ruleId, ruleName, ruleNaturalLanguage);
        naturalLanguageRulesDTO.add(ruleDTO);

        when(rulesEngineService.getRules()).thenReturn(naturalLanguageRules);

        List<NaturalLanguageRuleDTO> result = rulesEngineController.getRules();

        verify(rulesEngineService, times(1)).getRules();
        assertEquals(naturalLanguageRulesDTO, result);
    }

}
