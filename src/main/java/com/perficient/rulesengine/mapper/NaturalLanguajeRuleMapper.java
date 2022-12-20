package com.perficient.rulesengine.mapper;

import com.perficient.rulesengine.dto.NaturalLanguageRuleDTO;
import com.perficient.rulesengine.model.NaturalLanguageRule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NaturalLanguajeRuleMapper {

    NaturalLanguageRule fromDTO(NaturalLanguageRuleDTO naturalLanguageRuleDTO);
    NaturalLanguageRuleDTO fromNaturalLanguageRule(NaturalLanguageRule naturalLanguageRule);

}
