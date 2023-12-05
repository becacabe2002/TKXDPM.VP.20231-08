package com.grp08.capstoneprojectg08.controller;


import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.repository.ImageRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.util.StringProcess;

import java.io.File;
import java.util.List;

public class HomeController extends BaseController{

    public HomeController(){
            super();
        }

    private boolean checkImageExist(Media media){
        // check if image exist in local storage in form of: "src/main/resources/com/grp08/capstoneprojectg08/assets/" + imageName
        String imageName = StringProcess.fromNameToImageName(media);
        // check in file path
        String imagePath = "src/main/resources/com/grp08/capstoneprojectg08/assets/" + imageName;
        return new File(imagePath).exists();
    }

    // get Media and its image from database
    public List<Media> getMediaAndImage(){
        List<Media> mediaList = mediaRepo.findAllMedias();
        // check
        for (Media m : mediaList){
            String imagePath = null;
            // if image not exist in local storage
            if(!checkImageExist(m)){
                // get image from database and save to local storage
                imagePath = imageRepo.getMediaImage(m);
            }
            if(m.getImageUrl() == null && imagePath != null){
                m.setImageUrl(imagePath);
            }
        }
        return mediaList;
        // return a List<Media> that contains media together with it`s proper imagepath
    }

    // get Media base on its category and name filter
    public List<? extends Media> getMediaByCategoryAndName(MediaCategory mediaCategory, String name){
        return switch (mediaCategory) {
            case Book -> mediaRepo.findBooksFilterByTitle(name);
            case DVD -> mediaRepo.findDVDsFilterByTitle(name);
            case CD -> mediaRepo.findCDsFilterByTitle(name);
            default -> mediaRepo.findAllMedias();
            // TODO: findMediasFilterByTitle(name)
            // but
            // get a List<Media> with media fall on one of the three category Book , DVD ,  CD or media in general
        };
    }

    // add Media to cart (if it not in cart)
    public void addMediaToCart(Media media, int quantity){
        if(checkMediaItemInCart(media.getID()) == null){
            Cart userCart = getCart();
            userCart.getCartItems().add(new CartItem(media.getID(), quantity));
            userCart.calculate();
        } else {
            infomationAlert.setTitle("Information Dialog");
            infomationAlert.setHeaderText(null);
            infomationAlert.setContentText("This item is already in your cart!");
            infomationAlert.showAndWait();
        }
    }

    public List<Media> getAllMedia() {
        return mediaRepo.findAllMedias();
        // Return a List<Media> containing all media items from the repository
    }
}
