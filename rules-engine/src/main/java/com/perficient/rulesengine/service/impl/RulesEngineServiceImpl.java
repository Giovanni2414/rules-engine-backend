package com.perficient.rulesengine.service.impl;

import com.perficient.rulesengine.model.Rule;
import com.perficient.rulesengine.repository.RuleRepository;
import com.perficient.rulesengine.service.RulesEngineService;
import io.github.jamsesso.jsonlogic.JsonLogic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RulesEngineServiceImpl implements RulesEngineService {

    RuleRepository ruleRepository;


    @Override
    public Rule saveRule(Rule rule) {
        return ruleRepository.save(rule);
    }
}
