package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.Register;
import com.perficient.rulesengine.model.Rule;

import java.util.List;
import java.util.UUID;

public interface RulesEngineService {

    Rule saveRule(Rule rule);

    List<Register> evaluateRule(UUID ruleId);

}
