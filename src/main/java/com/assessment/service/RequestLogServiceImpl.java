package com.assessment.service;

import com.assessment.model.RequestLog;
import com.assessment.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private static final String REQUEST = "REQUEST";
    private static final String KO = "KO";

    @Autowired
    RequestLogRepository repository;

    @Override
    public void convertLogIntoListOfFilteresRequests(String filename) throws IOException {

        repository.clearRequests();

        InputStream inputStream = new FileInputStream(filename);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String log = br.readLine();
        while (log != null) {
            if (log.startsWith(REQUEST)) {
                convertLogIntoRequestLog(log);
            }
            log = br.readLine();
        }
        br.close();
    }

    @Override
    public void convertLogIntoRequestLog(String line) {

        line = line.replaceAll("\t\t", "\t");

        String[] part = line.split("\t");

        String type = part[0];
        int id = Integer.parseInt(part[1]);
        String name = part[2];
        long start = Long.parseLong(part[3]);
        long end = Long.parseLong(part[4]);
        String status = part[5];
        String message = "";
        if ("KO".equals(status)) {
            message = part[6];
        }

        repository.saveRequest(new RequestLog(type, id, name, start, end, status, message));
    }

    @Override
    public int getTotalRequests() {
        return repository.getTotalRequests();
    }

    @Override
    public int getTotalFailedRequest() {
        List<RequestLog> failed = repository.requestLogList.stream()
                .filter(request -> request.getStatus().equals(KO))
                .collect(Collectors.toList());
        return failed.size();
    }

}
