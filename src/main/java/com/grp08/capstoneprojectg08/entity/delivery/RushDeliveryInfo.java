package com.grp08.capstoneprojectg08.entity.delivery;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RushDeliveryInfo {
    private Date shippingTime;
    private String rushDeliveryInstructions;

    public RushDeliveryInfo(){
    }
    public RushDeliveryInfo(Date shippingTime, String rushDeliveryInstructions){
        this.shippingTime = shippingTime;
        this.rushDeliveryInstructions = rushDeliveryInstructions;
    }
}
