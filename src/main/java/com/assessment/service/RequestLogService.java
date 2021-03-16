package com.assessment.service;

import com.assessment.model.RequestLog;

import java.io.IOException;
import java.util.List;

public interface RequestLogService {

	List<RequestLog> convertLogIntoListOfFilteresRequests(String filename) throws IOException;

	void convertLogIntoRequestLog(String line);

}