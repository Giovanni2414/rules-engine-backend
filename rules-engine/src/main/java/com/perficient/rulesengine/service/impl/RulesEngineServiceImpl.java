package com.perficient.rulesengine.service.impl;

import com.perficient.rulesengine.model.Rule;
import com.perficient.rulesengine.repository.RuleRepository;
import com.perficient.rulesengine.service.RulesEngineService;
import io.github.jamsesso.jsonlogic.JsonLogic;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class RulesEngineServiceImpl implements RulesEngineService {

    private final RuleRepository ruleRepository;

    @Override
    public Rule saveRule(Rule rule) {
        rule.setExpressionBody(rule.getExpressionBody().replace("and", "&&"));
        rule.setExpressionBody(rule.getExpressionBody().replace("or", "||"));
        return ruleRepository.save(rule);
    }

    @SneakyThrows
    @Override
    public boolean evaluteRule(UUID ruleId) {
        JsonLogic jsonLogic = new JsonLogic();
        Rule rule = ruleRepository.findById(ruleId).orElse(null);

        JSONObject jsonObject = new JSONObject(rule.getExpression1());

        List<String> list = new ArrayList<>();
        jsonObject.keys().forEachRemaining(list::add);

        System.out.println();

        Map<String, Boolean> context = new java.util.HashMap<String, Boolean>();
        context.put("exp1", (boolean) jsonLogic.apply(rule.getExpression1(), null));
        context.put("exp2", (boolean) jsonLogic.apply(rule.getExpression2(), null));
        context.put("exp3", (boolean) jsonLogic.apply(rule.getExpression3(), null));
        context.put("exp4", (boolean) jsonLogic.apply(rule.getExpression4(), null));

        return (boolean) MVEL.eval(rule.getExpressionBody(), context);
    }
}
