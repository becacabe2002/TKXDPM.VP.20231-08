package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.user.User;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private UserRole role;
    private String username;

    private Cart cart = null; // for storing temporary cart

    private Invoice invoice = null;
    public UserSession(){
        this.cart = new Cart();
    }

    public void setUser(User user){
        this.role = user.getUserRole();
        this.username = user.getUname();
    }

    public static UserSession getInstance(){
        if(instance == null){
            instance = new UserSession();
        }
        return instance;
    }

    public UUID getCurrentUserUID(){
        // get current user's UUID based on username through UserRepo
        return null;
    }
    public void emptyCart(){
        this.cart = new Cart();
    }
}
