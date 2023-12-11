package com.grp08.capstoneprojectg08.response;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.scene.image.Image;


public class MediaAndImageResponse {
    /*
     * this is response class for home screen
     */
    private Media media;
    private Image image;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public MediaAndImageResponse(){
    }

    public MediaAndImageResponse(Media media, Image image){
        this.media = media;
        this.image = image;
    }
}
