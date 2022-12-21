package com.perficient.rulesengine.api;

import com.perficient.rulesengine.dto.NaturalLanguageRuleDTO;
import com.perficient.rulesengine.dto.RegisterDTO;
import com.perficient.rulesengine.dto.RuleDTO;
import com.perficient.rulesengine.model.Rule;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RequestMapping("/rules")
public interface RulesEngineAPI {

    @PostMapping
    RuleDTO saveRule(@RequestBody RuleDTO ruleDTO);

    @GetMapping("eval/{ruleId}")
    List<RegisterDTO> evaluateRule(@PathVariable UUID ruleId);

    @GetMapping
    List<NaturalLanguageRuleDTO> getRules();

    @DeleteMapping("{ruleId}")
    void removeRule(@PathVariable UUID ruleId);

}
