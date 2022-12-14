package com.perficient.rulesengine.repository;

import com.perficient.rulesengine.model.Column;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DBMetadataRepository extends CrudRepository<Column, UUID> {

    @Query(value = "SELECT column_name, udt_name, ordinal_position as column_id " +
            "FROM information_schema.columns " +
            "WHERE table_schema = 'public' AND table_name = ?1", nativeQuery = true)
    public List<Column> getTableColumns(String tableName);

}
