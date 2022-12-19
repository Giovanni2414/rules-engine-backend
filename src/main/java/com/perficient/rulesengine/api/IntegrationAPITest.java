package com.perficient.rulesengine.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/CICD")
public interface IntegrationAPITest {
    @GetMapping
    public String testCICD();
}
