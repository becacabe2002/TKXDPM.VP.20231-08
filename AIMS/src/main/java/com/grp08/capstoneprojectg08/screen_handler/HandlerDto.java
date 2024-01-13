package com.grp08.capstoneprojectg08.screen_handler;

import com.grp08.capstoneprojectg08.screen_handler.cart.CartItemHandler;
import com.grp08.capstoneprojectg08.screen_handler.cart.CartScreenHandler;
import com.grp08.capstoneprojectg08.screen_handler.delivery.DeliveryFormHandler;
import com.grp08.capstoneprojectg08.screen_handler.delivery.RushDeliveryFormHandler;
import com.grp08.capstoneprojectg08.screen_handler.home.HomeScreenHandler;
import com.grp08.capstoneprojectg08.screen_handler.home.MediaItemHandler;
import com.grp08.capstoneprojectg08.screen_handler.invoice.InvoiceScreenHandler;
import com.grp08.capstoneprojectg08.screen_handler.payment.PaymentScreenHandler;
import com.grp08.capstoneprojectg08.screen_handler.product.ProductDetailScreenHandler;
import com.grp08.capstoneprojectg08.util.UserSession;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class HandlerDto {
    private static volatile HandlerDto INSTANCE;
    
    private CartItemHandler cartItemHandler;
    private CartScreenHandler cartScreenHandler;
    private DeliveryFormHandler deliveryFormHandler;
    private RushDeliveryFormHandler rushDeliveryFormHandler;
    private HomeScreenHandler homeScreenHandler;
    private MediaItemHandler mediaItemHandler;
    private InvoiceScreenHandler invoiceScreenHandler;
    private PaymentScreenHandler paymentScreenHandler;
    private ProductDetailScreenHandler productDetailScreenHandler;
    
    private HandlerDto(){
    }

    public void registerDeliverFormHandler(DeliveryFormHandler handler){
        this.deliveryFormHandler = handler;
    }
    public DeliveryFormHandler getRunningDeliveryFormHandler(){
        return this.deliveryFormHandler;
    }

    public static HandlerDto getInstance() {
        HandlerDto res = INSTANCE;
        if(res == null){
            synchronized (HandlerDto.class){
                res = INSTANCE;
                if(res == null){
                    INSTANCE = res = new HandlerDto();
                }
            }
        }
        return res;
    }
}
