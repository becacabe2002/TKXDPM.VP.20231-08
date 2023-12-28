package com.grp08.capstoneprojectg08.request;

public class AddItemCartRequest {
    private int mediaId;

    private int quantity;

    public AddItemCartRequest() {
    }

    public AddItemCartRequest(int mediaId, int quantity) {
        this.mediaId = mediaId;
        this.quantity = quantity;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
