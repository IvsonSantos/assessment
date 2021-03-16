package com.assessment.service;

import java.io.IOException;

public interface RequestLogService {

	void convertLogIntoListOfFilteresRequests(String filename) throws IOException;

	void convertLogIntoRequestLog(String line);

	int getTotalRequests();

	int getTotalFailedRequest();

}