package com.perficient.rulesengine.api;

import com.perficient.rulesengine.dto.RuleDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rules")
public interface RulesEngineAPI {

    @PostMapping
    RuleDTO saveRule(@RequestBody RuleDTO ruleDTO);

}
