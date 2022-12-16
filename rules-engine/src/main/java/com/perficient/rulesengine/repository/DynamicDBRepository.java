package com.perficient.rulesengine.repository;

import com.perficient.rulesengine.model.DynamicData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DynamicDBRepository extends CrudRepository<DynamicData, Integer> {

    @Query(value = "SELECT count(tableName) AS id , json_agg(tableName) as data FROM #{#entityName} as tableName", nativeQuery = true)
    DynamicData getDataAsJson();

}
