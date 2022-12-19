package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.mapper.ColumnMapper;
import com.perficient.rulesengine.mapper.ColumnMapperImpl;
import com.perficient.rulesengine.service.DBMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class DBMetadataControllerTest {

    private DBMetadataService dbMetadataService;

    private ColumnMapper columnMapper;

    private DBMetadataController dbMetadataController;

    @BeforeEach
    public void init(){
        dbMetadataService = mock(DBMetadataService.class);
        columnMapper = new ColumnMapperImpl();
        dbMetadataController = new DBMetadataController(dbMetadataService, columnMapper);
    }

    @Test
    public void getTableColumnsTest(){
        String tableName = "data";
        dbMetadataController.getTableColumns(tableName);
        verify(dbMetadataService, times(1)).getTableColumns(tableName);
    }

}
