package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.media.Media;

public class StringProcess {
    public static String fromNameToImageName(Media media){
        // trim space at the beginning and end of name
        // replace space in the middle of name with underscore
        // lowercase all characters
        String processName = media.getTitle().trim().replaceAll("\\s+", "_").toLowerCase();
        return media.getID() + "_" + processName + ".jpg";
    }
}
