package com.perficient.rulesengine.controller;

import com.perficient.rulesengine.api.IntegrationAPITest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationControllerTest implements IntegrationAPITest {


    @Override
    public String testCICD() {
        return "Spring application running";
    }
}
