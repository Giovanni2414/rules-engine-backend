package com.perficient.rulesengine.service.impl;

import com.perficient.rulesengine.constant.DataType;
import com.perficient.rulesengine.dto.ColumnDTO;
import com.perficient.rulesengine.model.Column;
import com.perficient.rulesengine.repository.DBMetadataRepository;
import com.perficient.rulesengine.service.DBMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class DBMetadataServiceImpl implements DBMetadataService {

    DBMetadataRepository dbMetadataRepository;

    @Override
    public List<Column> getTableColumns(String tableName) {return normalizeData(dbMetadataRepository.getTableColumns(tableName));
    }

    private List<Column> normalizeData(List<Column> collect) {
        for(Column column:collect){
            formatType(column, DataType.TEXT);
            formatType(column, DataType.NUMERIC);
            formatType(column, DataType.BOOLEAN);
        }
        return collect;
    }

    private void formatType(Column column, DataType dataType){
        if(Arrays.asList(dataType.getDbType()).contains(column.getType())){
            column.setType(dataType.getValue());
        }
    }
}
