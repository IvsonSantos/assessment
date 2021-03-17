package com.assessment.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestService {

    @Autowired
    private RequestLogService requestLogService;

    @Test
    void contextLoads() throws IOException {

        long diffInMs = 1558086349421l - 1558086340382l;
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        System.out.println(diffInSec);

        requestLogService.convertLogIntoListOfFilteresRequests("data-test.log");

        requestLogService.getAverageResponseTimeForEndpoints();

    }
}
