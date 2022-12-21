package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.NaturalLanguageRule;
import com.perficient.rulesengine.model.Register;
import com.perficient.rulesengine.model.Rule;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface RulesEngineService {

    Rule saveRule(Rule rule);

    List<Register> evaluateRule(UUID ruleId);

    List<NaturalLanguageRule> getRules();

    void removeRule(@PathVariable UUID ruleId);

}
