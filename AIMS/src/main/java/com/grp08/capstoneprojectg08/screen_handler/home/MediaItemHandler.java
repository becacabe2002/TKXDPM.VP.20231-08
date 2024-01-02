package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
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


import javax.swing.*;
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


    //TODO : addToCartBtn to add media to cart
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
                } else {
                    // Handle failure
                    // Show an error message (you can use a dialog, toast, or any UI component)
                    System.out.println("Failed to add item to cart. Message: " + response.getResponseMessage());
                }

                // Clear the input field after adding to cart
                cartitemQuantityInput.clear();
            } catch (NumberFormatException e) {
                // Handle parsing errors or invalid input format
                System.out.println("Invalid quantity format. Please enter a valid number.");
            }
        } else {
            // Handle a situation where the quantity is invalid
            System.out.println("Invalid quantity value or no media selected.");
        }
    }

    // TODO : toProductDetailBtn to see the detail of the media
    @FXML
    private void redirectToProductDetail() {
        if (media != null) {
            try {
                int productId = media.getID(); // Replace this with the actual method to get the product ID

                // Create a JSON object for the request body
                JSONObject requestBody = new JSONObject();
                requestBody.put("mediaId", productId);

                // Set the endpoint for sending the JSON request
                String productDetailEndpoint = "/home/media-details"; // Replace this with your actual product detail endpoint

                // Create a BaseRequest for the endpoint
                BaseRequest baseRequest = new BaseRequest(RequestMethod.GET, productDetailEndpoint, requestBody);

                // Use the EndpointRegister to handle the request
                EndpointRegister endpointRegister = new EndpointRegister();
                BaseResponse response = endpointRegister.handleRequest(baseRequest);
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/product-detail-screen.fxml"));
                Parent root = loader.load();

                // Get the current stage
                Stage currentStage = (Stage) toProductDetailBtn.getScene().getWindow();

                // Create a new stage for the product detail screen
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                // Close the current stage
                currentStage.close();
                ProductDetailScreenHandler controller = new ProductDetailScreenHandler();
                controller.loadMediaDetails(response);

                // Show the new stage
                stage.show();
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
        alert.showAndWait();
    }
}
