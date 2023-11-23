package com.grp08.capstoneprojectg08.entity.media;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Media {
    protected int ID;
    protected MediaCategory category;
    protected int price; // gia ca
    protected int stockQuantity; //stock
    protected String title;
    protected int value;// gia tri
    protected String imageUrl = null;
    protected boolean fastShipping; // if the media support shipping or not

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
}
