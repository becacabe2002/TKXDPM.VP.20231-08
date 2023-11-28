package com.grp08.capstoneprojectg08;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.repository.ImageRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.util.StringProcess;

public class TestImageUpload {
    public static void main(String[] args) {
        Media media = MediaRepo.findMediaById(3);
//        boolean upload = ImageRepo.saveMediaImage("src/main/resources/com/grp08/capstoneprojectg08/assets/ImportImages/shaw_redem.jpg", media);
//        System.out.println(upload);
        System.out.println(StringProcess.fromNameToImageName(media));
        String imagePath = ImageRepo.getMediaImage(media);
        System.out.println(imagePath);
    }
}
