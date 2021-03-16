package com.assessment;

import com.assessment.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssessmentTestApplication implements CommandLineRunner {
	
	@Autowired
	private RequestLogService requestLogService;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentTestApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		requestLogService.convertLogIntoListOfFilteresRequests("data 2.log");

		System.out.println("Total of REQUESTS: " + requestLogService.getTotalRequests());

		System.out.println("Total of FAILED requests: " + requestLogService.getTotalFailedRequest());
    }

}
