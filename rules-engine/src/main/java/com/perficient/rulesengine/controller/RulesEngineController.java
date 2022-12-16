package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.RulesEngineAPI;
import com.perficient.rulesengine.dto.RuleDTO;
import com.perficient.rulesengine.mapper.RuleMapper;
import com.perficient.rulesengine.service.RulesEngineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class RulesEngineController implements RulesEngineAPI {

    private RulesEngineService rulesEngineService;

    private RuleMapper ruleMapper;

    @Override
    public RuleDTO saveRule(RuleDTO ruleDTO) {
        return ruleMapper.fromRule(rulesEngineService.saveRule(ruleMapper.fromDTO(ruleDTO)));
    }

    @Override
    public boolean evaluateRule(UUID ruleId) {
        return rulesEngineService.evaluteRule(ruleId);
    }
}
