package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.Rule;

import java.util.UUID;

public interface RulesEngineService {

    Rule saveRule(Rule rule);

    boolean evaluteRule(UUID ruleId);

}
