package com.perficient.rulesengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "DATA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicData {

    @Id
    private int id;

    @Column(name = "data")
    private String data;

}
