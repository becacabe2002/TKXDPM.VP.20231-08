package com.grp08.capstoneprojectg08.entity.cart;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import lombok.Getter;

@Getter
public class CartItem {
    private int mediaId;
    private int quantity;
    private int subPrice;// = media.price * quantity
    private MediaRepo mediaRepo;
    private Media media = null;

    public CartItem(int mediaId, int quantity) {
        try{
            media = mediaRepo.findMediaById(mediaId);
            if(media == null) throw new NullPointerException("Media not found");
            this.mediaId = mediaId;
            this.quantity = quantity;
            this.subPrice = media.getPrice() * quantity;
        } catch (NullPointerException e){
            System.err.println(e.getMessage());
        }
    }

    private void addQuantity(){
        this.quantity += 1;
        this.subPrice = this.quantity * media.getPrice();
    }
    private void minusQuantity(){
        this.quantity -= 1;
        this.subPrice = this.quantity * media.getPrice();
    }
}
