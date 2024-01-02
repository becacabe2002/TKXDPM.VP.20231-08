package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.service.CartService;
import com.grp08.capstoneprojectg08.service.OrderService;
import com.grp08.capstoneprojectg08.util.UserSession;
import org.json.JSONArray;
import org.json.JSONException;
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

    /**
     * Create order from cart, should be called before
     * {@link #calculateShippingFee()} and {@link #updateNormalDeliveryInfo(BaseRequest)}
     * <ul>
     *    <li>Check if all items in cart are still available (need to implement {@link CartService#checkUnavailableMediaItemsInCart()} beforehand)</li>
     *    <li>Check if user is logged in and is customer (default login as "user1")</li>
     *    <li>Create invoice, attach order to it and set it to user's session</li>
     * </ul>
     * @return response
     * <ul>
     *     <li>{@link ResponseCode#OK} -> Order created successfully</li>
     *     <li>{@link ResponseCode#BAD_REQUEST} -> Cart is empty</li>
     *     <li>{@link ResponseCode#UNAUTHORIZED} -> User is not logged in or is not customer</li>
     * </ul>
     */
    public BaseResponse createOrder(){
        UserSession userSession = UserSession.getInstance();
        if(userSession.getUsername() != null
        && userSession.getRole() == UserRole.user){
            if(orderService.createOrderFromCart()){
                return new BaseResponse(ResponseCode.OK, "Success");
            } else {
                return new BaseResponse(ResponseCode.BAD_REQUEST, "Cart is empty");
            }
        } else {
            return new BaseResponse(ResponseCode.UNAUTHORIZED, "User is not logged in or is not customer");
        }
    }

    // calculate normal shipping fee
    public BaseResponse calculateShippingFee(){
        orderService.calculateShippingFee();
        return new BaseResponse(ResponseCode.OK, "Success");
    }

    // update invoice with normal delivery info

    /**
     * Update invoice with normal delivery info
     * <br><b><i> Fields need to be validated in the FE before updating !!</i></b>
     * @param baseRequest body contains:
     * <ul>
     *     <li>phone: "phone"</li>
     *     <li>province: "province"</li>
     *     <li>address: "address"</li>
     *     <li>shippingInstructions: "shippingInstructions"</li>
     * </ul>
     * @return
     */
    public BaseResponse updateNormalDeliveryInfo(BaseRequest baseRequest){
        JSONObject jsonObject = baseRequest.getBody();
        try{
            String name = UserSession.getInstance().getUsername();
            String phone = jsonObject.getString("phone");
            String province = jsonObject.getString("province");
            String address = jsonObject.getString("address");
            String shippingInstructions = jsonObject.getString("shippingInstructions");
            orderService.updateInvoiceWithDeliveryInfo(name, phone, province, address, shippingInstructions);
            return new BaseResponse(ResponseCode.OK, "Update delivery info successfully");
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }

    /**
     * Get invoice details
     * @return response which contains:
     * <ul>
     *     <li>name: "name"</li>
     *     <li>phone: "phone"</li>
     *     <li>province: "province"</li>
     *     <li>shippingMethod: "shippingMethod"</li>
     *     <li>detailAddress: "detailAddress"</li>
     *     <li>shippingInstruction: "shippingInstruction"</li>
     *     <li>fastDeliveryDate: "fastDeliveryDate"</li>
     *     <li>fastDeliveryInstruction: "fastDeliveryInstruction"</li>
     *     <li>subTotal: "subTotal"</li>
     *     <li>vat: "vat"</li>
     *     <li>shippingFee: "shippingFee"</li>
     *     <li>total: "total"</li>
     * </ul>
     */
    public BaseResponse getInvoiceDetails(){
        UserSession userSession = UserSession.getInstance();
        Invoice invoice = userSession.getInvoice();
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCode.OK);
        response.setResponseMessage("");
        JSONObject responseBody = new JSONObject();
        responseBody.put("name", userSession.getUsername());
        responseBody.put("phone", invoice.getOrder().getDeliveryInfo().getPhone());
        responseBody.put("province", invoice.getOrder().getDeliveryInfo().getProvince());
        if(invoice.getOrder().getDeliveryInfo().getRushDeliveryInfo() != null){
            responseBody.put("shippingMethod", "Include Rush Delivery");
            responseBody.put("detailAddress", invoice.getOrder().getDeliveryInfo().getAddress());
            responseBody.put("shippingInstruction", invoice.getOrder().getDeliveryInfo().getInstructions());
            responseBody.put("fastDeliveryDate", invoice.getOrder().getDeliveryInfo().getRushDeliveryInfo().getShippingTime().toString());
            responseBody.put("fastDeliveryInstruction", invoice.getOrder().getDeliveryInfo().getRushDeliveryInfo().getRushDeliveryInstructions());
        } else{
            responseBody.put("shippingMethod", "Normal");
            responseBody.put("detailAddress", invoice.getOrder().getDeliveryInfo().getAddress());
            responseBody.put("shippingInstruction", invoice.getOrder().getDeliveryInfo().getInstructions());
            responseBody.put("fastDeliveryDate", "");
            responseBody.put("fastDeliveryInstruction", "");
        }
        responseBody.put("subTotal", userSession.getCart().getSubTotal());
        responseBody.put("vat", userSession.getCart().getVat());
        responseBody.put("shippingFee", invoice.getOrder().getShippingFees());
        responseBody.put("total", userSession.getInvoice().getTotalAmount());
        response.setBody(responseBody);
        return response;
    }
}
