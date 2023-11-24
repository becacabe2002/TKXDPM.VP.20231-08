package com.grp08.capstoneprojectg08.entity.delivery;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RushDeliveryInfo getRushDeliveryInfo() {
        return rushDeliveryInfo;
    }

    public void setRushDeliveryInfo(RushDeliveryInfo rushDeliveryInfo) {
        this.rushDeliveryInfo = rushDeliveryInfo;
    }
}
