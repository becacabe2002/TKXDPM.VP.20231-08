package com.grp08.capstoneprojectg08.response;

import java.util.ArrayList;
import java.util.List;

public class HomeResponse {
    private int numberOfCartItems = 0;
    private List<MediaAndImageResponse> listMedia = new ArrayList<>();

    public HomeResponse() {
    }
    public List<MediaAndImageResponse> getListMedia() {
        return this.listMedia;
    }
    public void setListMedia(List<MediaAndImageResponse> listMedia) {
        this.listMedia = listMedia;
    }

    public int getNumberOfCartItems() {
        return this.numberOfCartItems;
    }
    public void setNumberOfCartItems(int numberOfCartItems) {
        this.numberOfCartItems = numberOfCartItems;
    }
}
