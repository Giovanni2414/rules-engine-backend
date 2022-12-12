package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.DBMetadataAPI;
import com.perficient.rulesengine.dto.ColumnDTO;
import com.perficient.rulesengine.service.DBMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DBMetadataController implements DBMetadataAPI {

    DBMetadataService dbMetadataService;

    @Override
    public List<ColumnDTO> getTableColumns(String tableName) {
        return null;
    }

}
