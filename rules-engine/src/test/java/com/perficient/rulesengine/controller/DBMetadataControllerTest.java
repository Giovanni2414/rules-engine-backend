package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.dto.ColumnDTO;
import com.perficient.rulesengine.mapper.ColumnMapper;
import com.perficient.rulesengine.mapper.ColumnMapperImpl;
import com.perficient.rulesengine.model.Column;
import com.perficient.rulesengine.service.DBMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<Column> columns = new ArrayList<>();
        Column columnName = new Column(1, "varchar", "name");
        Column columnAssociate = new Column(2, "bool", "is_associate");
        columns.add(columnName);
        columns.add(columnAssociate);

        List<ColumnDTO> columnsDTO = new ArrayList<>();
        ColumnDTO columnNameDTO = new ColumnDTO("varchar", "name");
        ColumnDTO columnAssociateDTO = new ColumnDTO("bool", "is_associate");
        columnsDTO.add(columnNameDTO);
        columnsDTO.add(columnAssociateDTO);

        when(dbMetadataService.getTableColumns(tableName)).thenReturn(columns);
        List<ColumnDTO> resultColumns = dbMetadataController.getTableColumns(tableName);
        verify(dbMetadataService, times(1)).getTableColumns(tableName);
        assertEquals(columnsDTO, resultColumns);
    }

}
