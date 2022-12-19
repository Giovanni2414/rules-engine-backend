package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.dto.RuleDTO;
import com.perficient.rulesengine.mapper.*;
import com.perficient.rulesengine.service.RulesEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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
        rulesEngineController.evaluateRule(ruleId);
        verify(rulesEngineService, times(1)).evaluateRule(ruleId);
    }

    @Test
    public void getRulesTest(){
        rulesEngineService.getRules();
        verify(rulesEngineService, times(1)).getRules();
    }

}
