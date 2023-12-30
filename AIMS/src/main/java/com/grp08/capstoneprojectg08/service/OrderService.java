package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import com.grp08.capstoneprojectg08.entity.delivery.RushDeliveryInfo;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.util.DateUnion;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.time.LocalDate;
import java.util.*;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class OrderService {
    private MediaService mediaService = new MediaService();

    private UserSession userSession = UserSession.getInstance();

    public OrderService() {
    }

    //create Order from Cart
    // check if user cart is empty or not
    public boolean createOrderFromCart(){
        if(!userSession.getCart().getCartItems().isEmpty()){
            Order order = new Order();
            order.fromCartToOrder(userSession.getCart());
            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            invoice.setTotalAmount((int) userSession.getCart().getTotal());
            invoice.getOrder().setDeliveryInfo(new DeliveryInfo());
            userSession.setInvoice(invoice);
            return true;
        } else{
            return false;
        }
    }

    // --- service for normal shipping ---

    // calculate normal shipping fee
    public void calculateShippingFee(){
        Invoice invoice = userSession.getInvoice();
        Random random = new Random();
        Map<Integer, Double> shippingFeeMap = new HashMap<>();
        for(OrderItem item : invoice.getOrder().getOrderItems()){
            double shippingFee = 0.01 * (random.nextInt(10) + 1) * item.getSubPrice();
            shippingFeeMap.put(item.getMediaId(), shippingFee);
        }
        // temporary update shipping fee to deliveryInfo
        Double temp = 0.0;
        for(Map.Entry<Integer, Double> entry : shippingFeeMap.entrySet()){
            temp += entry.getValue();
        }
        userSession.setFeeMap(shippingFeeMap);
        invoice.getOrder().setShippingFees(temp.intValue());
        invoice.setTotalAmount((int) userSession.getCart().getTotal() + invoice.getOrder().getShippingFees());
    }

    public void updateInvoiceWithDeliveryInfo(String name, String phone, String province, String address, String shippingInstructions){
//        if (!validateInputShippingForm(name, phone, address, shippingInstructions)) return;
        Invoice invoice = userSession.getInvoice();
        Order order = invoice.getOrder();
        DeliveryInfo deliveryInfo = order.getDeliveryInfo();
        deliveryInfo.setName(name);
        deliveryInfo.setPhone(phone);
        deliveryInfo.setAddress(address);
        deliveryInfo.setProvince(province);
        deliveryInfo.setInstructions(shippingInstructions);
    }


    // --- service for fast shipping ---
    public List<Media> getFastShippingSupportedOrderItems(){
        List<Media> supportedOrderItems = new ArrayList<>();
        List<OrderItem> orderItems = userSession.getInvoice().getOrder().getOrderItems();
        for(OrderItem orderItem : orderItems){
            Media media = mediaService.getMediaById(orderItem.getMediaId());
            if(media.isFastShipping()){
                supportedOrderItems.add(media);
            }
        }
        return supportedOrderItems;
    }

    public void updateFastShippingFee(){
        Map<Integer,Double> feeMap = userSession.getFeeMap();
        List<Media> supportedOrderItems = getFastShippingSupportedOrderItems();
        for(Media media : supportedOrderItems){
            feeMap.put(media.getID(), feeMap.get(media.getID()) * 1.3);
        }
        Double temp = 0.0;
        for(Map.Entry<Integer, Double> entry : feeMap.entrySet()){
            temp += entry.getValue();
        }
        userSession.getInvoice().getOrder().setShippingFees(temp.intValue());
        userSession.getInvoice().setTotalAmount((int) userSession.getCart().getTotal() + userSession.getInvoice().getOrder().getShippingFees());
    }

    public void updateRushOrderInfo(LocalDate shippingTime, String rushDeliveryInstructions){
//        if(validateInputRushDeliveryForm(shippingTime, rushDeliveryInstructions)){
        userSession.getInvoice().getOrder()
                .getDeliveryInfo()
                .setRushDeliveryInfo(new RushDeliveryInfo(DateUnion.SQLDateFromLocalDate(shippingTime), rushDeliveryInstructions));
//        }
    }

}
