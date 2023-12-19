package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.scene.image.Image;

public class ImageUtil {
    public static Image getMediaImage(Media media){
        Image image;
        if (media.getImageUrl() == null){
            image = switch (media.getCategory()) {
                case Book -> new Image("https://cdn-icons-png.flaticon.com/512/2232/2232688.png");
                case CD -> new Image("https://cdn-icons-png.flaticon.com/512/5730/5730221.png");
                case DVD -> new Image("https://cdn-icons-png.flaticon.com/512/1262/1262340.png");
                case All -> new Image("https://cdn-icons-png.flaticon.com/512/151/151279.png");
            };
        } else{
            // get image from URL
            image = new Image(media.getImageUrl());
        }
        return image;
    }
}
