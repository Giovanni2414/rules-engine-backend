package com.perficient.rulesengine.api;

import com.perficient.rulesengine.dto.ColumnDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequestMapping("/aws")
public interface AWSTestAPI {

    @GetMapping
    public ResponseEntity<String> health();

}
