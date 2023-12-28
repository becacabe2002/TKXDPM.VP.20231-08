package com.grp08.capstoneprojectg08.response;

import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class BaseResponse {
    private int responseCode;

    private String responseMessage;

    private JSONObject body;

    public BaseResponse(){
    }
    public BaseResponse(int responseCode, String responseMessage){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
