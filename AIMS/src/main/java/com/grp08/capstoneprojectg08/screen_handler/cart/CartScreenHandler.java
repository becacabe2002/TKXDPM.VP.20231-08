package com.grp08.capstoneprojectg08.screen_handler.cart;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class CartScreenHandler implements Initializable {
    private List<CartItem> cartItems = new ArrayList<>();

//    @FXML
//    private AnchorPane mainAnchorPane;
    @FXML
    private Button backHomeBtn;
    @FXML
    private Label SubtotalLabel;
    @FXML
    private Label VATLabel;
    @FXML
    private Label AmountLabel;
    @FXML
    private Button placeOrderBtn;
    @FXML
    private VBox cartItemsVBox;
    private final EndpointRegister endpointRegister = new EndpointRegister(); // Initialize your EndpointRegister

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Assume 'mediaItemList' contains the list of media items in the cart
        // Fetch media items from the cart and add them to the VBox
        int row = 0;
        cartItems = fetchCartItems(); // Replace this with your logic
        if (!cartItems.isEmpty()){
            for (CartItem cartItem : cartItems) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/cart-item.fxml"));
                    Parent cartItemPane = loader.load();
                    CartItemHandler controller = loader.getController();
                    controller.setCartItem(cartItem);

                    // Access the controller instance to set data for the cart item
                    // Method to set data in CartItemHandler

                    // Add the loaded cart item to the VBox
                    cartItemsVBox.getChildren().add(cartItemPane);
                } catch (IOException e) {
                    System.err.println("Failed to load cart item: " + e.getMessage());
                    // Handle exception appropriately
                }
                row++;
            }
        }
    }
    private List<CartItem> fetchCartItems() {
        List<CartItem> cartItemList = new ArrayList<>();

        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEndpoint("/cart/info");
        baseRequest.setMethod(RequestMethod.GET);
        BaseResponse response = endpointRegister.handleRequest(baseRequest);
        if (response.getResponseCode() == ResponseCode.OK) {
            JSONObject body = response.getBody();
            JSONArray cartItemsArray = body.getJSONArray("cartItems");
            if(cartItemsArray.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Your cart is empty");
                alert.setContentText("Please add some items to your cart");
                alert.showAndWait();
            } else{
                for (int i = 0; i < cartItemsArray.length(); i++) {
                    JSONObject tempObject = cartItemsArray.getJSONObject(i);
                    CartItem cartItem = createCartItemFromJson(tempObject);
                    cartItemList.add(cartItem);
                }
            }
            SubtotalLabel.setText(String.valueOf(body.getFloat("subTotal") + " $"));
            VATLabel.setText(String.valueOf(body.getFloat("vat")) + " $");
            AmountLabel.setText(String.valueOf(body.getFloat("total")) + " $");
        } else {
            System.out.println("Failed to fetch media items: " + response.getResponseMessage());
        }

        return cartItemList;
    }

    public CartItem createCartItemFromJson(JSONObject mediaJson) {
        // Your implementation of creating Media from JSON
        // Ensure proper exception handling and object creation
//        return null; // Replace null with your Media object
        JSONObject mediaObj = mediaJson.getJSONObject("media");
        int quantity = mediaJson.getInt("quantity");
        int subPrice = mediaJson.getInt("subPrice");
        Media media = createMediaFromJson(mediaObj);
        CartItem cartItem = new CartItem();
        cartItem.setMedia(media);
        cartItem.setQuantity(quantity);
        cartItem.setSubPrice(subPrice);
        cartItem.setMediaId(media.getID());
        return cartItem;
    }

    @FXML
    private void handleCloseButtonClick() {
        // Handle close button click event
        System.out.println("Close button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = (Stage) backHomeBtn.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
//            Stage currentStage = (Stage) backHomeBtn.getScene().getWindow();
//            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }

    @FXML
    private void handlePlaceOrderButtonClick() {
        // Handle close button click event
        System.out.println("place order button clicked");
        // TODO: check xem trong gio hang co hang nao unavailable hay ko? (gọi tới endpoint "/cart/check-unavailable")
        BaseRequest req = new BaseRequest();
        req.setEndpoint("/cart/check-unavailable");
        req.setMethod(RequestMethod.GET);
        BaseResponse response = endpointRegister.handleRequest(req);
        JSONArray jsonArray = response.getBody().getJSONArray("unavailableList");
        //  Neu co, hien thi thong bao loi va khong dieu huong
        if(jsonArray.length() > 0){
            String displayUnavailableList = "\n";
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject unavailableMedia = jsonArray.getJSONObject(i);
                displayUnavailableList += "* " + unavailableMedia.toString();
                displayUnavailableList += "\n";
            }
            showAlert("Error", "These items in cart are unavaiable: " +
                    displayUnavailableList + "Please change theses item in cart before placing again.", Alert.AlertType.WARNING);
        } else {
            BaseRequest bq = new BaseRequest();
            bq.setEndpoint("/order/create-order");
            bq.setMethod(RequestMethod.POST);
            response = endpointRegister.handleRequest(bq);
            if(response.getResponseCode() == ResponseCode.OK){
                showAlert("Success", "Order is created. Please fill in delivery form to complete the order.", Alert.AlertType.INFORMATION);
                try {
                    // Load the FXML file for the home screen
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/delivery-form.fxml"));
                    Parent root = loader.load();

                    // Create a new stage for the home screen
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    // Close the current product detail stage
                    Stage currentStage = (Stage) placeOrderBtn.getScene().getWindow();
                    currentStage.close();

                    // Show the home screen
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception accordingly
                }
            } else {
                showAlert("System Error", "There is an error in the systems. Please try again.", Alert.AlertType.ERROR);
            }

        }
        //  Neu khong, gọi tới endpoint để tạo Order và gắn vào invoice, sau đó mới chuyển hướng sang màn form

    }


    private Media createMediaFromJson(JSONObject mediaJson) {
        // Your implementation of creating Media from JSON
        // Ensure proper exception handling and object creation
//        return null; // Replace null with your Media object
        int ID = mediaJson.getInt("ID");
        MediaCategory category = mediaJson.getEnum(MediaCategory.class,"category");
        int price = mediaJson.getInt("price");
        int stockQuantity = mediaJson.getInt("stockQuantity");
        String title = mediaJson.getString("title");
        int value = mediaJson.getInt("value");
        String imageUrl = mediaJson.getString("imageUrl");
        boolean fastShipping = mediaJson.getBoolean("fastShipping");

        return new Media(ID, category, price, stockQuantity, title, value, imageUrl, fastShipping);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
