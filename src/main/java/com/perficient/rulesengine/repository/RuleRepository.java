package com.perficient.rulesengine.repository;

import com.perficient.rulesengine.model.Rule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RuleRepository extends CrudRepository<Rule, UUID> {

    List<Rule> deleteByRuleId(UUID ruleId);

}
