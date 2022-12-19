package com.perficient.rulesengine.repository;

import com.perficient.rulesengine.model.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RuleRepository extends CrudRepository<Rule, UUID> {
}
