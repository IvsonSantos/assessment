package com.assessment.model;

public class RequestLog {

    private String type;
    private int id;
    private String name;
    private long sent_time;
    private long received_time;
    private String status;
    private String message;

    public RequestLog() {

    }

    public RequestLog(String type, int id, String name, long sent_time, long received_time, String status, String message) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.sent_time = sent_time;
        this.received_time = received_time;
        this.status = status;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSentTime() {
        return sent_time;
    }

    public void setSentTime(long sent_time) {
        this.sent_time = sent_time;
    }

    public long getReceivedTime() {
        return received_time;
    }

    public void setReceivedTime(long received_time) {
        this.received_time = received_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", start=" + sent_time +
                ", end=" + received_time +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
