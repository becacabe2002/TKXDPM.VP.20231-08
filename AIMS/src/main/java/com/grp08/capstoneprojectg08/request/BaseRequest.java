package com.grp08.capstoneprojectg08.request;

import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class BaseRequest {
    private RequestMethod method;
    private String endpoint;
    private JSONObject body;

    public BaseRequest() {
    }
    public BaseRequest(RequestMethod method, String endpoint, JSONObject body) {
        this.method = method;
        this.endpoint = endpoint;
        this.body = body;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
