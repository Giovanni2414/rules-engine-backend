package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.RulesEngineAPI;
import com.perficient.rulesengine.config.InitialDataConfig;
import com.perficient.rulesengine.dto.NaturalLanguageRuleDTO;
import com.perficient.rulesengine.dto.RegisterDTO;
import com.perficient.rulesengine.dto.RuleDTO;
import com.perficient.rulesengine.mapper.NaturalLanguajeRuleMapper;
import com.perficient.rulesengine.mapper.RegisterMapper;
import com.perficient.rulesengine.mapper.RuleMapper;
import com.perficient.rulesengine.service.RulesEngineService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Import({InitialDataConfig.class})
public class RulesEngineController implements RulesEngineAPI {

    private RulesEngineService rulesEngineService;

    private RuleMapper ruleMapper;

    private RegisterMapper registerMapper;

    private NaturalLanguajeRuleMapper naturalLanguajeRuleMapper;

    @Override
    public RuleDTO saveRule(RuleDTO ruleDTO) {
        return ruleMapper.fromRule(rulesEngineService.saveRule(ruleMapper.fromDTO(ruleDTO)));
    }

    @Override
    public List<RegisterDTO> evaluateRule(UUID ruleId) {
        return rulesEngineService.evaluateRule(ruleId).stream().map(registerMapper::fromRegister).collect(Collectors.toList());
    }

    @Override
    public List<NaturalLanguageRuleDTO> getRules() {
        return rulesEngineService.getRules().stream().map(naturalLanguajeRuleMapper::fromNaturalLanguageRule).collect(Collectors.toList());
    }
}
