package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.Column;
import com.perficient.rulesengine.repository.DBMetadataRepository;
import com.perficient.rulesengine.service.impl.DBMetadataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DBMetadataServiceTest {

    private DBMetadataRepository dbMetadataRepository;
    private DBMetadataService dbMetadataService;

    @BeforeEach
    public void init(){
        dbMetadataRepository = mock(DBMetadataRepository.class);
        dbMetadataService = new DBMetadataServiceImpl(dbMetadataRepository);
    }

    @Test
    public void getTableColumns(){
        String tableName = "DATA";
        List<Column> columns = new ArrayList<>();
        when(dbMetadataRepository.getTableColumns("DATA")).thenReturn(columns);
        dbMetadataService.getTableColumns(tableName);
        verify(dbMetadataRepository, times(1)).getTableColumns(tableName);
    }

}
