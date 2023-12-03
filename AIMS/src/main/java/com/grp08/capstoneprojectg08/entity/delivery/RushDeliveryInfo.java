package com.grp08.capstoneprojectg08.entity.delivery;


import java.sql.Date;


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
}
