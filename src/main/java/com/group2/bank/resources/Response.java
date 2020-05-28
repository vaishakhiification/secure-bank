package com.group2.bank.resources;

/**
 * A response class is created to simplify Responses when sent to the client.
 * It is a generic Response class
 */
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
