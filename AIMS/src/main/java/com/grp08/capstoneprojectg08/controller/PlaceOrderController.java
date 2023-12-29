package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.UserSession;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class PlaceOrderController extends BaseController{

    public PlaceOrderController() {
        super();
    }

    // check if cart item is still available, return list of unavailable cart items
    public BaseResponse checkCartItemsAvailability(){
        List<CartItem> unavailableCartItems = cartService.checkUnavailableMediaItemsInCart();
        JSONArray unavailableList = new JSONArray();
        for(CartItem cartItem: unavailableCartItems){
            JSONObject unavailableItem = cartItem.toJSON();
            unavailableList.put(unavailableItem);
        }
        JSONObject returnJSON = new JSONObject();
        returnJSON.put("unavailableList", unavailableList);
        BaseResponse response = new BaseResponse(ResponseCode.OK, "");
        response.setBody(returnJSON);
        return response;
    }

    // TODO: need to create Order from Cart first
    // check if all items in cart are still available
    // create invoice, attach order to it and set it to user session
    // check if user is logged in and is customer
    public void createOrder(){
//        // check if user cart is not empty
//       if(getCart().getCartItems().isEmpty()){
//           warningAlert.setTitle("Warning");
//           warningAlert.setHeaderText(null);
//           warningAlert.setContentText("Your cart is empty. Please add item to cart before placing order.");
//           warningAlert.showAndWait();
//           return;
//       } else if(UserSession.getInstance().getRole() != UserRole.user || UserSession.getInstance().getUsername() == null){
//           warningAlert.setTitle("Warning");
//           warningAlert.setHeaderText(null);
//           warningAlert.setContentText("You must login as customer to place order.");
//           warningAlert.showAndWait();
//           return;
//       }
//       // create order from cart
//        UserSession.getInstance().setInvoice(new Invoice(getCart()));
    }

// validate input: name, phone, address, shipping instructions, (shipping time and Fast shipping instructions)
//    public boolean validateInputName(String name){
//        if (name.trim().isEmpty()){
//            warningAlert.setHeaderText("Invalid name");
//            warningAlert.setContentText("Name must not be empty.");
//            warningAlert.showAndWait();
//            return false;
//        } else return true;
//    }
//
//    public boolean validateInputPhone(String phone){
//        boolean check = true;
//        if(phone.length() != 10) check = false;
//        if (!phone.startsWith("0")) check = false;
//        try{
//            Integer.parseInt(phone);
//        } catch (NumberFormatException e){
//            check = false;
//        }
//        if(!check){
//            warningAlert.setHeaderText("Invalid phone number");
//            warningAlert.setContentText("Phone number must be 10 digits and start with 0.");
//            warningAlert.showAndWait();
//        }
//        return check;
//    }
//
//    public boolean validateInputAddress(String address){
//        if(address.trim().isEmpty()){
//            warningAlert.setHeaderText("Invalid address");
//            warningAlert.setContentText("Address must not be empty.");
//            warningAlert.showAndWait();
//            return false;
//        } else return true;
//    }
//
//    public boolean validateInputShippingInstructions(String shippingInstructions){
//        if(shippingInstructions.trim().isEmpty()){
//            warningAlert.setHeaderText("Invalid shipping instructions");
//            warningAlert.setContentText("Shipping instructions must not be empty.");
//            warningAlert.showAndWait();
//            return false;
//        } else return true;
//    }
//
//    public boolean validateInputShippingForm(String name, String phone, String address, String shippingInstructions){
//        return validateInputName(name)
//                && validateInputPhone(phone)
//                && validateInputAddress(address)
//                && validateInputShippingInstructions(shippingInstructions);
//    }

    // TODO: refactor to OrderService
    public Map<Integer, Double> calculateShippingFee(){
        Invoice invoice = UserSession.getInstance().getInvoice();
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
        invoice.getOrder().setShippingFees(temp.intValue());
        return shippingFeeMap;
    }

    // TODO: refactor to OrderService
    // update invoice with normal delivery info
    public void updateInvoiceWithDeliveryInfo(String name, String phone, String address, String shippingInstructions){
//        if (!validateInputShippingForm(name, phone, address, shippingInstructions)) return;
        Invoice invoice = UserSession.getInstance().getInvoice();
        Order order = invoice.getOrder();
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setName(name);
        deliveryInfo.setPhone(phone);
        deliveryInfo.setAddress(address);
        deliveryInfo.setInstructions(shippingInstructions);
        order.setDeliveryInfo(deliveryInfo);
    }

}
