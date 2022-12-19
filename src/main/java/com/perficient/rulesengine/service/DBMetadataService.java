package com.perficient.rulesengine.service;

import com.perficient.rulesengine.model.Column;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DBMetadataService {

    public List<Column> getTableColumns(@PathVariable String tableName);

}
