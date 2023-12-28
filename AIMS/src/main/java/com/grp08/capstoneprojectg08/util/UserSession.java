package com.grp08.capstoneprojectg08.util;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.user.User;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import com.grp08.capstoneprojectg08.repository.UserRepo;
import com.grp08.capstoneprojectg08.repository.UserRepoImplement;

import java.util.UUID;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 * <br>Implement singleton pattern for storing temporary user session
 */
public class UserSession {
    private static volatile UserSession instance; // thread safety
    private UserRole role = null;
    private String username = null;

    private Cart cart = null; // for storing temporary cart

    private Invoice invoice = null;
    private UserSession(){
        this.cart = new Cart();
    }

    public void setUser(User user){
        this.role = user.getUserRole();
        this.username = user.getUname();
    }

    public static UserSession getInstance(){
        // enhance performance
        UserSession result = instance; // -> access volatile variable only once time by using local variable
        if(result == null){
            synchronized (UserSession.class){
                result = instance;
                if(result == null){
                    instance = result = new UserSession();
                }
            }
        }
        return result;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public UUID getCurrentUserUID(){
        return new UserRepoImplement().findUserByUsername(this.username).getExternalUID();
    }
    public void emptyCart(){
        this.cart = new Cart();
    }

    public void emptyInvoice(){
        this.invoice = null;
    }
}
