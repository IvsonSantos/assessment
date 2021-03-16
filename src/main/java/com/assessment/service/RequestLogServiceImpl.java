package com.assessment.service;

import com.assessment.model.RequestLog;
import com.assessment.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private static final String REQUEST = "REQUEST";

    @Autowired
    RequestLogRepository repository;

    @Override
    public List<RequestLog> convertLogIntoListOfFilteresRequests(String filename) throws IOException {

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

        return RequestLogRepository.requestLogList;
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

        /*
        List<String> sources = Stream.of(headerSections)
                .filter(headerSection -> headerSection.split(" ")[0].equals(directive))
                .flatMap(headerSection -> Arrays.stream(headerSection.split(" ")))
                .collect(Collectors.toList());
        sources.removeIf(source -> source.equals(directive));
        return sources;

         */
    }




}
