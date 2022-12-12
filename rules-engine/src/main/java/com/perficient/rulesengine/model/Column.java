package com.perficient.rulesengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Column {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID columnId;

    @javax.persistence.Column(name = "udt_name")
    private String type;

    @javax.persistence.Column(name = "column_name")
    private String name;

}
