package com.perficient.rulesengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Column {

    @Id
    private int columnId;

    @javax.persistence.Column(name = "udt_name")
    private String type;

    @javax.persistence.Column(name = "column_name")
    private String name;

}
