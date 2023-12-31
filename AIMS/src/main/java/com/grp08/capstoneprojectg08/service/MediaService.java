package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepoImplement;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.StringProcess;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class MediaService {
    private MediaRepo mediaRepo = new MediaRepoImplement();

    public MediaService() {
    }


    // return list of media (not include images) responses by category and search string
    // image will be retrieved from ImageUtil
    public List<? extends Media> getMediaByCategoryAndName(String category, String name) throws IllegalArgumentException{
        try {
            MediaCategory mediaCategory = MediaCategory.valueOf(category);
            return switch (mediaCategory) {
                case Book -> mediaRepo.findBooksFilterByTitle(name);
                case DVD -> mediaRepo.findDVDsFilterByTitle(name);
                case CD -> mediaRepo.findCDsFilterByTitle(name);
                default -> mediaRepo.findAllMediaByTitle(name);
            };
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category");
        }
    }

    //return detail response base on media id
    public String getMediaDetails(int mediaId){
        Media media = mediaRepo.findMediaById(mediaId);
        return switch (media.getCategory()){
            case Book -> mediaRepo.findBookById(mediaId).toString();
            case CD -> mediaRepo.findCDById(mediaId).toString();
            case DVD -> mediaRepo.findDVDById(mediaId).toString();
            default -> null;
        };
    }
    public Media getMediaById(int mediaId){
        return mediaRepo.findMediaById(mediaId);
    }

    public void updateMediaItemStock(int mediaId, int quantity) {
        mediaRepo.reduceMediaQuantity(mediaId, quantity);
    }
}
