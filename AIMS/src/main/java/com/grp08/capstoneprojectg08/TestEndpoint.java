package com.grp08.capstoneprojectg08;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
<<<<<<< HEAD
import org.json.JSONArray;
=======
>>>>>>> c24e8ec (gitignore + add some screen handler)
import org.json.JSONObject;

public class TestEndpoint {
    private static EndpointRegister endpointRegister = new EndpointRegister();
    public static void main(String[] args) {
        BaseRequest baseRequest = new BaseRequest();
<<<<<<< HEAD
        baseRequest.setEndpoint("/home/medias");
        baseRequest.setMethod(RequestMethod.GET);
        JSONObject jsonObject = new JSONObject();
<<<<<<< HEAD
        jsonObject.put("category", "CD");
        jsonObject.put("name", "stick");
=======
        jsonObject.put("category", "All");
        jsonObject.put("name", "");
=======
        baseRequest.setEndpoint("/order/add-to-cart");
        baseRequest.setMethod(RequestMethod.POST);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mediaId", "1");
        jsonObject.put("quantity", "1");
>>>>>>> c24e8ec (gitignore + add some screen handler)
>>>>>>> f286dd2 (gitignore + add some screen handler)
        baseRequest.setBody(jsonObject);
        BaseResponse response = endpointRegister.handleRequest(baseRequest);
        System.out.println(response.getResponseCode());
        System.out.println(response.getResponseMessage());
<<<<<<< HEAD
        JSONArray jsonArray = response.getBody().getJSONArray("mediaItems");
        for(int i = 0; i < jsonArray.length(); i++){
            System.out.println(jsonArray.getJSONObject(i).toString());
        }
    }
}
=======
//       System.out.println(response.getBody().getJSONArray("mediaItems"));
    }
}
>>>>>>> c24e8ec (gitignore + add some screen handler)
