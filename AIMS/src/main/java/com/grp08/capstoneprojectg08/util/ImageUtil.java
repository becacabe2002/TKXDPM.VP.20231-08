package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class ImageUtil {
    private static Map<String, Image> imageMap = new HashMap<>();
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
            if(!imageMap.containsKey(media.getImageUrl()))
                imageMap.put(media.getImageUrl(), new Image(media.getImageUrl()));
            image = imageMap.get(media.getImageUrl());
        }
        return image;
    }
}
