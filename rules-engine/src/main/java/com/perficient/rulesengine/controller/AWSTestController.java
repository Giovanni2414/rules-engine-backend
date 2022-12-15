package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.AWSTestAPI;
import com.perficient.rulesengine.service.DBMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AWSTestController implements AWSTestAPI {

    @Override
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Spring boot application running on EC2 instance from AWS!!!");
    }

}
