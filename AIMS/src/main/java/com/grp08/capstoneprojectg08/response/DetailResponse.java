package com.grp08.capstoneprojectg08.response;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.scene.image.Image;

public class DetailResponse {
    private Media media;
    private Image image;

    private String detailInfo;
    public DetailResponse(){
    }
    public DetailResponse(Media media, Image image, String detailInfo){
        this.media = media;
        this.image = image;
        this.detailInfo = detailInfo;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
