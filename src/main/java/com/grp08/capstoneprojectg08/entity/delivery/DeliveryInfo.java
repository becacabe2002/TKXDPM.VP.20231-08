package com.grp08.capstoneprojectg08.entity.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryInfo {
    private int id;
    private String address;
    private String instructions;
    private String province;
    private String name; // receiver's name
    private RushDeliveryInfo rushDeliveryInfo = null;

    public DeliveryInfo(int id, String address, String instructions, String province, String name) {
        this.id = id;
        this.address = address;
        this.instructions = instructions;
        this.province = province;
        this.name = name;
    }
}
