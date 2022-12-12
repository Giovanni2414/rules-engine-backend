package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.Column;

import java.util.List;

public interface DBMetadaService {

    public List<Column> getTableColumns(String tableName);

}
