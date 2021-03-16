package com.assessment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.service.RequestLogService;

@RestController
@RequestMapping("/logs")
public class RequestLogController {
	
	@Autowired
	private RequestLogService requestLogService;

	@GetMapping("/{filename}")
	public void convertLogIntoListOfFilteresRequests(@PathVariable String filename) throws IOException {
		requestLogService.convertLogIntoListOfFilteresRequests(filename);
	}
}
