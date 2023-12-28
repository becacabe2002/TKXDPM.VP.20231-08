package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.delivery.RushDeliveryInfo;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.util.DateUnion;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class PlaceRushOrderController extends BaseController{

    public PlaceRushOrderController(){
        super();
    }

    // get the list of fast shipping supported order items
    // for display purpose
    // TODO: change to return OrderItem, refactor to Order Service
    public List<Integer> getFastShippingSupportedOrderItems(){
        List<Integer> supportedOrderItems = new ArrayList<>();
        List<OrderItem> orderItems = UserSession.getInstance().getInvoice().getOrder().getOrderItems();
        for(OrderItem orderItem : orderItems){
            Media media = mediaService.getMediaById(orderItem.getMediaId());
            if(media.isFastShipping()){
                supportedOrderItems.add(orderItem.getMediaId());
            }
        }
        return supportedOrderItems;
    }

    // validate input: shipping time, rush delivery instructions
    // shipping time must be at least 1 day from now
    // rush delivery instructions must not be empty
//    public boolean validateInputShippingTime(LocalDate shippingTime){
//        if(shippingTime.isBefore(LocalDate.now().plusDays(1))){
//            warningAlert.setHeaderText("Invalid shipping time");
//            warningAlert.setContentText("Shipping time must be at least 1 day from now.");
//            warningAlert.showAndWait();
//            return false;
//        } else return true;
//    }
//    public boolean validateInputRushDeliveryInstructions(String rushDeliveryInstructions){
//        if(rushDeliveryInstructions.trim().isEmpty()){
//            warningAlert.setHeaderText("Invalid rush delivery instructions");
//            warningAlert.setContentText("Rush delivery instructions must not be empty.");
//            warningAlert.showAndWait();
//            return false;
//        } else return true;
//    }
//    public boolean validateInputRushDeliveryForm(LocalDate shippingTime, String rushDeliveryInstructions){
//        return validateInputShippingTime(shippingTime) && validateInputRushDeliveryInstructions(rushDeliveryInstructions);
//    }

    // update shipping fee of supported cart items
    // fast shipping fee = 130% of normal shipping fee
    // TODO: refactor to Order Service
    public void updateFastShippingFee(Map<Integer,Double> feeMap){
        List<Integer> supportedOrderItems = getFastShippingSupportedOrderItems();
        for(Integer mediaId : supportedOrderItems){
            feeMap.put(mediaId, feeMap.get(mediaId) * 1.3);
        }
        // TODO: update fast shipping fee to invoice
    }

    // TODO: refactor to OrderService
    // update rush order information
    // input: Order reference, shipping time, rush delivery instructions
    public void updateRushOrderInfo(LocalDate shippingTime, String rushDeliveryInstructions){
//        if(validateInputRushDeliveryForm(shippingTime, rushDeliveryInstructions)){
            UserSession.getInstance().getInvoice().getOrder()
                    .getDeliveryInfo()
                    .setRushDeliveryInfo(new RushDeliveryInfo(DateUnion.SQLDateFromLocalDate(shippingTime), rushDeliveryInstructions));
//        }
    }

}
