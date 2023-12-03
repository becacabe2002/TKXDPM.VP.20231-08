package com.grp08.capstoneprojectg08;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.repository.ImageRepo;
import com.grp08.capstoneprojectg08.repository.ImageRepoImplement;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepoImplement;
import com.grp08.capstoneprojectg08.util.StringProcess;

public class TestImageUpload {
    public static void main(String[] args) {
        MediaRepo mediaRepo = new MediaRepoImplement();
        ImageRepo imageRepo = new ImageRepoImplement();
        for (int i = 1; i <= 30; i++){
            Media media = mediaRepo.findMediaById(i);
//            boolean upload = imageRepo.saveMediaImage("src/main/resources/com/grp08/capstoneprojectg08/assets/ImportImages/"+ i + ".jpg", media);
//            System.out.println(upload);
            System.out.println(StringProcess.fromNameToImageName(media));
            String imagePath = imageRepo.getMediaImage(media);
            System.out.println(imagePath);
        }
    }
}
