package com.assessment;

import com.assessment.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AssessmentTestApplication implements CommandLineRunner {
	
	@Autowired
	private RequestLogService requestLogService;

	private List requestLogs = new ArrayList();

	public static void main(String[] args) {
		SpringApplication.run(AssessmentTestApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		requestLogs = requestLogService.convertLogIntoListOfFilteresRequests("data 2.log");
		//System.out.println(requestLogs);

		System.out.println(requestLogService.getTotalRequests());
    }

}
