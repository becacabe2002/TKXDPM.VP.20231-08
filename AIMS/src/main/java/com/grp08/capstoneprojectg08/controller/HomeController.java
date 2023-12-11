package com.grp08.capstoneprojectg08.controller;


import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.repository.ImageRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.request.LoadHomeInfoRequest;
import com.grp08.capstoneprojectg08.response.HomeResponse;
import com.grp08.capstoneprojectg08.service.CartService;
import com.grp08.capstoneprojectg08.service.MediaService;
import com.grp08.capstoneprojectg08.util.StringProcess;
import com.grp08.capstoneprojectg08.util.UserSession;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;
import java.util.Map;

public class HomeController extends BaseController{
    private MediaService mediaService = new MediaService();
    private CartService cartService = new CartService();

    public HomeController(){
            super();
        }

    public void init(){
        mediaService.loadImage();
        UserSession.getInstance();
    }

    // get Media base on its category and name filter by send request
    public HomeResponse getHomeInfo(LoadHomeInfoRequest request){
        mediaService.loadImage();
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setNumberOfCartItems(cartService.getNumberOfCartItems());
        homeResponse.setListMedia(mediaService.getMediaByCategoryAndName(request.getCategory(), request.getName()));
        return homeResponse;
    }

    // add Media to cart (if it not in cart)
    // TODO: need to pass info through request
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
}
