package com.perficient.rulesengine.mapper;

import com.perficient.rulesengine.dto.RuleDTO;
import com.perficient.rulesengine.model.Rule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RuleMapper {
    Rule fromDTO(RuleDTO ruleDTO);
    RuleDTO fromRule(Rule rule);
}
