package com.grp08.capstoneprojectg08.entity.media;

import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class Media {
    protected int ID;

    protected MediaCategory category; // falls into one of three Book , CD , DVD
    protected int price; // gia ca
    protected int stockQuantity; //stock
    protected String title;
    protected int value;// gia tri
    protected String imageUrl = null; // in form: "@../assets/MediaImages/<image_name>.png"
    protected boolean fastShipping; // if the media support shipping or not

    public Media(int ID, MediaCategory category, int price, int stockQuantity, String title, int value, boolean fastShipping){
    }

    public Media(){};

    public Media(int id, MediaCategory category, int price, int quantity, String title, int value, String imageUrl, boolean fastShipping) {
        this.ID = id;
        this.category = category;
        this.price = price;
        this.stockQuantity = quantity;
        this.title = title;
        this.value = value;
        this.imageUrl = imageUrl;
        this.fastShipping = fastShipping;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public MediaCategory getCategory() {
        return category;
    }

    public void setCategory(MediaCategory category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFastShipping() {
        return fastShipping;
    }

    public void setFastShipping(boolean fastShipping) {
        this.fastShipping = fastShipping;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", this.getID());
        jsonObject.put("category", this.getCategory());
        jsonObject.put("price", this.getPrice());
        jsonObject.put("stockQuantity", this.getStockQuantity());
        jsonObject.put("title", this.getTitle());
        jsonObject.put("value", this.getValue());
        jsonObject.put("imageUrl", this.getImageUrl());
        jsonObject.put("fastShipping", this.isFastShipping());
        return jsonObject;
    }
}
