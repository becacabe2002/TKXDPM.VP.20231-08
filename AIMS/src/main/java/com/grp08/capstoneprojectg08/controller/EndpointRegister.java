package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class EndpointRegister {
    private final HomeController homeController = new HomeController();
    private final PaymentController paymentController = new PaymentController();
    private final PlaceOrderController placeOrderController = new PlaceOrderController();
    private final PlaceRushOrderController placeRushOrderController = new PlaceRushOrderController();
    private final ViewCartController viewCartController = new ViewCartController();

    public EndpointRegister(){
    }

    public BaseResponse handleRequest(BaseRequest baseRequest){
        BaseResponse baseResponse = null;
        RequestMethod requestMethod = baseRequest.getMethod();
        String endpoint = baseRequest.getEndpoint();
        if (requestMethod == RequestMethod.POST){
            /*
             * process payment, tra ve thong tin de hien thi tren man hinh thanh toan
             * -> neu thanh toan thanh cong, xoa gio hang, luu thong tin vao db
             * -> neu thanh toan that bai, hien dialog bao thanh toan that bai, vui long thuc hien lai o man hinh thanh toan
             */
            if(baseRequest.getEndpoint().equals("/order/payment")){
                return paymentController.processPayment(baseRequest);
            }

            /*
             * Add an item to cart
             */
            if(baseRequest.getEndpoint().equals("/cart/add-item")){
                return viewCartController.addItemToCart(baseRequest);
            }

            /*
             * Create order from cart, attach it to invoice
             */
            if(baseRequest.getEndpoint().equals("/order/create-order")) {
                return placeOrderController.createOrder();
            }

        } else if(requestMethod == RequestMethod.GET){

            /*
             * Get media, filter by name and type
             */
            if(baseRequest.getEndpoint().equals("/home/medias")){
                return homeController.getFilterMedia(baseRequest);
            }

            /*
             * Get current user cart info:
             * - number of cart items: "numberOfItems"
             * - list of cart items: "cartItems"
             * - subtotal price: "subTotal"
             * - vat: "vat"
             * - total (subtotal + vat): "total"
             */
            if(baseRequest.getEndpoint().equals("/cart/info")){
                return viewCartController.getCartInfo();
            }

            /*
             * get list of unavailable cart items
             */
            if(baseRequest.getEndpoint().equals("/cart/check-unavailable")){
                return viewCartController.checkCartItemsAvailability();
            }

            /*
             * Get details of a media item
             */
            if(baseRequest.getEndpoint().equals("/home/media-details")){
                return homeController.getMediaDetails(baseRequest);
            }

            /*
             * Get fast shipping supported item in user cart
             * (for display in rush delivery form)
             */
            if(baseRequest.getEndpoint().equals("/order/fast-shipping-supported")){
                return placeRushOrderController.getFastShippingSupportedItems();
            }

            /*
             * Get user's invoice detail
             * -> need to update invoice with delivery info (rush delivery info if exist)
             *  , and calculate shipping fee before get invoice detail
             */
            if(baseRequest.getEndpoint().equals("/order/invoice-details")) {
                return placeOrderController.getInvoiceDetails();
            }

        } else if(requestMethod == RequestMethod.PUT){
            /*
             * change quantity of a item in cart
             * recalculate subtotal price
             */
            if(baseRequest.getEndpoint().equals("/cart/change-item-quantity")){
                return viewCartController.changeCartItemQuantity(baseRequest);
            }

            /*
             * Update info from delivery form
             */
            if(baseRequest.getEndpoint().equals("/order/update-delivery-info")){
                return placeOrderController.updateNormalDeliveryInfo(baseRequest);
            }

            /*
             * Update normal shipping fee
             */
            if(baseRequest.getEndpoint().equals("/order/update-normal-shipping-fee")){
                return placeOrderController.calculateShippingFee();
            }

            /*
             * Update info from rush delivery form
             */
            if(baseRequest.getEndpoint().equals("/order/update-rush-delivery-info")){
                return placeRushOrderController.updateFastShippingInfo(baseRequest);
            }

            /*
             * Update fast shipping fee
             */
            if(baseRequest.getEndpoint().equals("/order/update-fast-shipping-fee")){
                return placeRushOrderController.updateFastShippingFee();
            }

        } else if(requestMethod == RequestMethod.DELETE){
            // delete item from cart
            if(baseRequest.getEndpoint().equals("/cart/remove-item")){
                return viewCartController.removeItemFromCart(baseRequest);
            }
        }
        else{
            baseResponse = new BaseResponse();
            baseResponse.setResponseCode(ResponseCode.BAD_REQUEST);
            baseResponse.setResponseMessage("Invalid request");
            baseResponse.setBody(null);
        }
        return baseResponse;
    }
}