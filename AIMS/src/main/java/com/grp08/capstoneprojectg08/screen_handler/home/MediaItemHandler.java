package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.ImageUtil;
import com.grp08.capstoneprojectg08.screen_handler.product.ProductDetailScreenHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MediaItemHandler implements Initializable {

    @FXML
    private Button addToCartBtn;

    @FXML
    private Button toProductDetailBtn;

    @FXML
    private ImageView MediaImage;

    @FXML
    private TextField cartitemQuantityInput;

    @FXML
    private Label mediaTitleLb;

    @FXML
    private Label mediaCategoryLb;

    @FXML
    private Label mediaPriceLb;

    @FXML
    private Text mediaStockLb;

//    @FXML
//    private Label stockLabel;

    private Media media;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the UI components with the provided media object
        initializeMedia();
        addToCartBtn.setOnAction(event -> addToCart());
    }

    private void initializeMedia() {
        if (this.media != null) {
            mediaTitleLb.setText(this.media.getTitle());
            mediaCategoryLb.setText(this.media.getCategory().toString());
            mediaPriceLb.setText(this.media.getPrice() + " $");
            mediaStockLb.setText(String.valueOf(this.media.getStockQuantity()));

            // Initialize ImageView with the image URL
            long startTime = System.currentTimeMillis();
            Image image = ImageUtil.getMediaImage(this.media);
            MediaImage.setImage(image);
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);
            System.out.println("Image retrieval time: " + duration);
        }
    }

    public void setMedia(Media media) {
        this.media = media;
        initializeMedia();
    }

    public Media getMedia() {
        return media;
    }


    @FXML
    private void addToCart() {
        int quantity = handleInputNumberChange();

        if (quantity > 0 && media != null) {
            try {
                // Retrieve the media ID
                int mediaId = media.getID(); // Replace this with the actual way to get media ID

                // Create a JSON request body with media ID and quantity
                JSONObject requestBody = new JSONObject();
                requestBody.put("mediaId", mediaId);
                requestBody.put("quantity", quantity);

                // Set the endpoint for adding an item to the cart
                String cartEndpoint = "/cart/add-item"; // Replace this with your actual cart endpoint

                // Create a BaseRequest for the endpoint
                BaseRequest baseRequest = new BaseRequest(RequestMethod.POST, cartEndpoint, requestBody);

                // Use the EndpointRegister to handle the request
                EndpointRegister endpointRegister = new EndpointRegister();
                BaseResponse response = endpointRegister.handleRequest(baseRequest);

                // Process the response
                if (response != null && response.getResponseCode() == ResponseCode.OK) {
                    // Handle success
                    // Show a success message (you can use a dialog, toast, or any UI component)
                    System.out.println("Item added to cart successfully!");
                    showAlert("Success", "Item added to cart successfully!", Alert.AlertType.INFORMATION);
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
                        Parent mediaItemRoot = loader.load();
                        HomeScreenHandler homeScreenHandler = loader.getController();
                        BaseRequest request = new BaseRequest();
                        request.setMethod(RequestMethod.GET);
                        request.setEndpoint("/cart/info");
                        BaseResponse res = endpointRegister.handleRequest(request);
                        JSONObject body = res.getBody();
                        int numberItems = body.getInt("numberOfItems");
                        homeScreenHandler.NumberItemInCartLabel.setText(String.valueOf(numberItems));
                    } catch (IOException e){
                        System.err.println(e.getMessage());
                    }
                } else {
                    // Handle failure
                    // Show an error message (you can use a dialog, toast, or any UI component)
                    System.out.println("Failed to add item to cart. Message: " + response.getResponseMessage());
                    showAlert("Error", "Failed to add item to cart. \nMessage: " + response.getResponseMessage(), Alert.AlertType.ERROR);
                }

                // Clear the input field after adding to cart
                cartitemQuantityInput.clear();
            } catch (NumberFormatException e) {
                // Handle parsing errors or invalid input format
                System.out.println("Invalid quantity format. Please enter a valid number.");
                showAlert("Error", "Invalid quantity format. Please enter a valid number.", Alert.AlertType.ERROR);
            }
        } else {
            // Handle a situation where the quantity is invalid
            System.out.println("Invalid quantity value or no media selected.");
            showAlert("Error", "Invalid quantity value or no media selected.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void redirectToProductDetail() {
        if (media != null) {
            try {
                int productId = media.getID();
                JSONObject requestBody = new JSONObject();
                requestBody.put("mediaId", productId);
                String productDetailEndpoint = "/home/media-details";
                BaseRequest baseRequest = new BaseRequest(RequestMethod.GET, productDetailEndpoint, requestBody);
                EndpointRegister endpointRegister = new EndpointRegister();
                BaseResponse response = endpointRegister.handleRequest(baseRequest);
                JSONObject bodyResponse = response.getBody();
                String details = bodyResponse.getString("detail");
                JSONObject mediaJSON = bodyResponse.getJSONObject("media");
                Media media = createMediaFromJson(mediaJSON);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/product-detail-screen.fxml"));
                Parent root = loader.load();
                ProductDetailScreenHandler controller = loader.getController();
                controller.setMedia(media, details);
                Stage currentStage = (Stage) toProductDetailBtn.getScene().getWindow();
                currentStage.setScene(new Scene(root));
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception accordingly
            }
        }
    }

    @FXML
    private int handleInputNumberChange() {
        String enteredNumber = cartitemQuantityInput.getText();
        try {
            int quantity = Integer.parseInt(enteredNumber);
            if (quantity > 0) {
                return quantity;
            } else {
                showAlert("Error", "Quantity should be a positive value.", Alert.AlertType.ERROR);
                return -1;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid quantity format. Please enter a valid number.", Alert.AlertType.ERROR);
            return -1;
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setOnCloseRequest(event -> {
           try{
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
               Parent root = loader.load();

               // Create a new stage for the home screen
               Stage stage = (Stage) mediaCategoryLb.getScene().getWindow();
               stage.setScene(new Scene(root));

               // Show the home screen
               stage.show();
           } catch (IOException e){
               System.err.println(e.getMessage());
           }
        });
        alert.showAndWait();
    }
    private Media createMediaFromJson(JSONObject mediaJson) {
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
}
