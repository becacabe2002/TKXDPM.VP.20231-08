package com.grp08.capstoneprojectg08.entity.delivery;


import org.json.JSONObject;

import java.sql.Date;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class RushDeliveryInfo {
    private Date shippingTime;
    private String rushDeliveryInstructions;

    public RushDeliveryInfo(){
    }
    public RushDeliveryInfo(Date shippingTime, String rushDeliveryInstructions){
        this.shippingTime = shippingTime;
        this.rushDeliveryInstructions = rushDeliveryInstructions;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getRushDeliveryInstructions() {
        return rushDeliveryInstructions;
    }

    public void setRushDeliveryInstructions(String rushDeliveryInstructions) {
        this.rushDeliveryInstructions = rushDeliveryInstructions;
    }
    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shippingTime", this.shippingTime.toString());
        jsonObject.put("rushDeliveryInstructions", this.rushDeliveryInstructions);
        return jsonObject;
    }
}
