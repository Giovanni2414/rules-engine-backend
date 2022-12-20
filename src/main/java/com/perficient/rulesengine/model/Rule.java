package com.perficient.rulesengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "rule")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID ruleId;

    private String expressionBody;

    @Column(name = "expression_1")
    private String expression1;

    @Column(name = "expression_2")
    private String expression2;

    @Column(name = "expression_3")
    private String expression3;

    @Column(name = "expression_4")
    private String expression4;

    @Column(name = "rule_name")
    private String ruleName;

    @PrePersist
    public void generateId() { this.ruleId = UUID.randomUUID();}

}
