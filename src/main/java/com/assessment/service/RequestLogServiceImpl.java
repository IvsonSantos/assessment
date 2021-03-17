package com.assessment.service;

import com.assessment.model.RequestLog;
import com.assessment.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private static final String REQUEST = "REQUEST";
    private static final String KO = "KO";
    private static final String OK = "OK";

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

    @Override
    public int getAverageResponseTimeForEndpoints() {

        List<String> endpoints = repository.requestLogList.stream()
                .filter(request -> request.getStatus().equals(OK))
                .map(request -> request.getName())
                .distinct()
                .collect(Collectors.toList());

        endpoints.stream().forEach(
                endpoint -> getAverageForEndpoint(endpoint)
        );

        return 0;
    }

    private double getAverageForEndpoint(String endpoint) {
        List<RequestLog> endpointlist =
                repository.requestLogList.stream()
                        .filter(request -> request.getName().equals(endpoint))
                        .collect(Collectors.toList());

        List<Long> times = new ArrayList<>();

        endpointlist.stream().forEach(requestLog -> {
            long diffInMils = requestLog.getReceivedTime() - requestLog.getSentTime();
            times.add(diffInMils);
        });

        LongSummaryStatistics stats = times.stream()
                .mapToLong((x) -> x)
                .summaryStatistics();

        System.out.println("AVERAGE " + endpoint + " " + stats.getAverage());

        if (times.size() >= 99) {
            System.out.println("PERCENTILE 95 " + getPercentile(times, 95));
            System.out.println("PERCENTILE 99 " + getPercentile(times, 99));
        }

        return stats.getAverage();
    }
    private Long getPercentile(List<Long> times, int position) {
        int index = (int) Math.ceil((position / 100f) * times.size());
        return times.get(index);
    }

}
