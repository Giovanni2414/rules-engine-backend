package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.DBMetadataAPI;
import com.perficient.rulesengine.constant.DataType;
import com.perficient.rulesengine.dto.ColumnDTO;
import com.perficient.rulesengine.mapper.ColumnMapper;
import com.perficient.rulesengine.service.DBMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DBMetadataController implements DBMetadataAPI {

    DBMetadataService dbMetadataService;
    ColumnMapper columnMapper;

    @Override
    public List<ColumnDTO> getTableColumns(String tableName) {
        return dbMetadataService.getTableColumns(tableName).stream().map(columnMapper::fromColumn).collect(Collectors.toList());
    }
}
