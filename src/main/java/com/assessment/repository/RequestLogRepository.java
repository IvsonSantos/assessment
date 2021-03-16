package com.assessment.repository;

import com.assessment.model.RequestLog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestLogRepository {

    public static List<RequestLog> requestLogList = new ArrayList<>();

    public void saveRequest(RequestLog requestLog) {
        requestLogList.add(requestLog);
    }

    public void clearRequests() {
        requestLogList.clear();
    }

    public int getTotalRequests() {
        return requestLogList.size();
    }

}
