package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.AWSTestAPI;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AWSTestController implements AWSTestAPI {

    @Override
    public String health() {
        return "Spring boot application running on EC2 instance from AWS!!!";
    }

}
