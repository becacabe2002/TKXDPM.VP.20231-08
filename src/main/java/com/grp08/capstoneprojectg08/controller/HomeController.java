package com.grp08.capstoneprojectg08.controller;


import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.repository.MediaRepo;

import java.util.List;

public class HomeController extends BaseController{
    // TODO: get Media and its image from database

    // TODO: get Media base on its category and name filter
    public List<? extends Media> getMediaByCategoryAndName(MediaCategory mediaCategory, String name){
        return switch (mediaCategory) {
            case Book -> new MediaRepo().findBooksFilterByTitle(name);
            case DVD -> new MediaRepo().findDVDsFilterByTitle(name);
            case CD -> new MediaRepo().findCDsFilterByTitle(name);
            default -> new MediaRepo().findAllMedias();
        };
    }

    // TODO: add Media to cart (if it not in cart)
}
