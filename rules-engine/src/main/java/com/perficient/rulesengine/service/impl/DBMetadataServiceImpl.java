package com.perficient.rulesengine.service.impl;

import com.perficient.rulesengine.model.Column;
import com.perficient.rulesengine.repository.DBMetadataRepository;
import com.perficient.rulesengine.service.DBMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DBMetadataServiceImpl implements DBMetadataService {

    DBMetadataRepository dbMetadataRepository;

    @Override
    public List<Column> getTableColumns(String tableName) {
        return dbMetadataRepository.getTableColumns(tableName);
    }
}
