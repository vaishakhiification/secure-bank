package com.group2.bank.resources;

//Class that represents a general JSON response object
public class Response {

    private long statusCode;

    private String responseMessage;

    public Response(long statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
