package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.repository.ImageRepo;
import com.grp08.capstoneprojectg08.repository.ImageRepoImplement;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepoImplement;
import com.grp08.capstoneprojectg08.response.MediaAndImageResponse;
import com.grp08.capstoneprojectg08.util.ImageSession;
import com.grp08.capstoneprojectg08.util.StringProcess;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MediaService {
    private MediaRepo mediaRepo = new MediaRepoImplement();
    private ImageRepo imageRepo = new ImageRepoImplement();
    private ImageSession imageSession = ImageSession.getInstance();

    public MediaService() {
    }

    public void loadImage(){
        Map<String, Image> imageMap = imageSession.getImageMap();
        List<Media> mediaList = mediaRepo.findAllMedias();
        for(Media media : mediaList){
            String tempName = StringProcess.fromNameToImageName(media);
            if(!imageMap.containsKey(tempName)){
                Image image = imageRepo.getMediaImage(media);
                imageMap.put(tempName, image);
            }
        }
    }

    // return list of media and image responses by category and search string
    public List<MediaAndImageResponse> getMediaByCategoryAndName(String category, String name) {
        MediaCategory mediaCategory = MediaCategory.valueOf(category);
        List<MediaAndImageResponse> mediaAndImageResponseList = new ArrayList<>();
        List<? extends Media> mediaList = switch (mediaCategory) {
            case Book -> mediaRepo.findBooksFilterByTitle(name);
            case DVD -> mediaRepo.findDVDsFilterByTitle(name);
            case CD -> mediaRepo.findCDsFilterByTitle(name);
            default -> mediaRepo.findAllMediaByTitle(name);
        };
        for(Media media : mediaList){
            String tempName = StringProcess.fromNameToImageName(media);
            Image image = imageSession.getImageMap().get(tempName);
            mediaAndImageResponseList.add(new MediaAndImageResponse(media, image));
        }
        return mediaAndImageResponseList;
    }

    //TODO: return detail response base on media id

}
