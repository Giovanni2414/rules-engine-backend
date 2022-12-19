package com.perficient.rulesengine.api;

import com.perficient.rulesengine.dto.NaturalLanguageRuleDTO;
import com.perficient.rulesengine.dto.RegisterDTO;
import com.perficient.rulesengine.dto.RuleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/rules")
public interface RulesEngineAPI {

    @PostMapping
    RuleDTO saveRule(@RequestBody RuleDTO ruleDTO);

    @PostMapping("/{ruleId}")
    List<RegisterDTO> evaluateRule(@PathVariable UUID ruleId);

    @GetMapping
    List<NaturalLanguageRuleDTO> getRules();
}
