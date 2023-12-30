package com.grp08.capstoneprojectg08;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestEndpoint {
    private static EndpointRegister endpointRegister = new EndpointRegister();
    public static void main(String[] args) {
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEndpoint("/home/medias");
        baseRequest.setMethod(RequestMethod.GET);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", "CD");
        jsonObject.put("name", "stick");
        baseRequest.setBody(jsonObject);
        BaseResponse response = endpointRegister.handleRequest(baseRequest);
        System.out.println(response.getResponseCode());
        System.out.println(response.getResponseMessage());
        JSONArray jsonArray = response.getBody().getJSONArray("mediaItems");
        for(int i = 0; i < jsonArray.length(); i++){
            System.out.println(jsonArray.getJSONObject(i).toString());
        }
    }
}