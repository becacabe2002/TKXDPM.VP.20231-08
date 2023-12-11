package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;

public class ImageSession {
    private Map<String, Image> imageMap;

    private static ImageSession instance;

    private ImageSession(){
        this.imageMap = new java.util.HashMap<>();
    }

    public static ImageSession getInstance(){
        if(instance == null){
            instance = new ImageSession();
        }
        return instance;
    }
    public Map<String, Image> getImageMap() {
        return this.imageMap;
    }
}
