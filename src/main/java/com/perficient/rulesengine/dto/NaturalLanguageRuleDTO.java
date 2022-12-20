package com.perficient.rulesengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaturalLanguageRuleDTO {

    private String id;

    private String name;

    private String ruleNaturalLanguage;
}
