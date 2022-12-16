package com.perficient.rulesengine.api;

import com.perficient.rulesengine.dto.RuleDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/rules")
public interface RulesEngineAPI {

    @PostMapping
    RuleDTO saveRule(@RequestBody RuleDTO ruleDTO);

    @PostMapping("/{ruleId}")
    boolean evaluateRule(@PathVariable UUID ruleId);

}
