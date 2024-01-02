package com.grp08.capstoneprojectg08.screen_handler.cart;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;


import javafx.fxml.FXML;
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



public class CartScreenHandler {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button backHomeBtn;
    @FXML
    private Button placeOrderBtn;
    @FXML
    private VBox cartItemsVBox;
    private final EndpointRegister endpointRegister = new EndpointRegister(); // Initialize your EndpointRegister
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Assume 'mediaItemList' contains the list of media items in the cart
        // Fetch media items from the cart and add them to the VBox
        int row = 0;
        List<Media> mediaItemList = fetchMediaItemsFromCart(); // Replace this with your logic
        for (Media mediaItem : mediaItemList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/cart-item.fxml"));
                AnchorPane cartItemPane = loader.load();

                // Access the controller instance to set data for the cart item
                CartItemHandler controller = loader.getController();
                controller.setMediaItemData(mediaItem); // Method to set data in CartItemHandler

                // Add the loaded cart item to the VBox
                cartItemsVBox.getChildren().add(cartItemPane);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception appropriately
            }
            row++;
        }
    }
    private List<Media> fetchMediaItemsFromCart() {
        List<Media> mediaList = new ArrayList<>();

        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEndpoint("/home/medias");
        baseRequest.setMethod(RequestMethod.GET);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", "All");
        jsonObject.put("name", "");
        baseRequest.setBody(jsonObject);

        BaseResponse response = endpointRegister.handleRequest(baseRequest);
        if (response.getResponseCode() == ResponseCode.OK) {
            JSONArray mediaArray = response.getBody().getJSONArray("mediaItems");
            for (int i = 0; i < mediaArray.length(); i++) {
                JSONObject tempObject = mediaArray.getJSONObject(i);
                Media media = createMediaFromJson(tempObject);
                mediaList.add(media);
            }
        } else {
            System.out.println("Failed to fetch media items: " + response.getResponseMessage());
        }
        return mediaList;
    }

    public Media createMediaFromJson(JSONObject mediaJson) {
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
    }
}
