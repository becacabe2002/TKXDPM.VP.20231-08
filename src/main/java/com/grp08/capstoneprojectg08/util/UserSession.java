package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private String username;
    private String password;

    private Cart cart;
    public UserSession(){
        this.cart = new Cart();
    }

    public static UserSession getInstance(){
        if(instance == null){
            instance = new UserSession();
        }
        return instance;
    }

    public void emptyCart(){
        this.cart = new Cart();
    }
}
