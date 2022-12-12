package com.perficient.rulesengine.api;

import com.perficient.rulesengine.dto.ColumnDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/metadata")
public interface DBMetadataAPI {

    @GetMapping
    public List<ColumnDTO> getTableColumns(@PathVariable String tableName);

}
