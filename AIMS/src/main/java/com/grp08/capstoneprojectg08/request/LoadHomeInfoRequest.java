package com.grp08.capstoneprojectg08.request;

public class LoadHomeInfoRequest {
    private String category;
    private String name;

    public LoadHomeInfoRequest() {
    }

    public LoadHomeInfoRequest(String category, String name) {
        this.category = category;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
