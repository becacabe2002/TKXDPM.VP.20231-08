package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.ImageUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import javafx.scene.image.Image;
import javafx.scene.text.Text;
import org.json.JSONObject;


import javax.swing.*;
import java.io.File;
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
    private TextField inputNumberTextField;

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

    @FXML
    private TextField cartitemQuantityInput;

    private Media media;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the UI components with the provided media object
        initializeMedia();
    }

    private void initializeMedia() {
        if (this.media != null) {
            mediaTitleLb.setText(this.media.getTitle());
            mediaCategoryLb.setText(this.media.getCategory().toString());
            mediaPriceLb.setText(this.media.getPrice() + " $");
            mediaStockLb.setText(String.valueOf(this.media.getStockQuantity()));

            // Initialize ImageView with the image URL
            Image image = ImageUtil.getMediaImage(this.media);
            MediaImage.setImage(image);
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

                // Create a BaseRequest for the endpoint
                BaseRequest baseRequest = new BaseRequest(RequestMethod.POST, "/cart/add-item", requestBody);

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
                inputNumberTextField.clear();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/productDetail.fxml"));
                Parent root = loader.load();

                // Get the controller associated with the loaded FXML
                ProductDetailController productDetailController = loader.getController();
                productDetailController.setMedia(media);

                // Create a new stage and display the product detail view
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception accordingly
            }
        }
    }

    @FXML
    private int handleInputNumberChange() {
        String enteredNumber = inputNumberTextField.getText();
        try {
            int quantity = Integer.parseInt(enteredNumber);
            return quantity > 0 ? quantity : -1; // Ensure the quantity is a positive value
        } catch (NumberFormatException e) {
            // Handle invalid input: show an error message
            showAlert("Error", "Invalid quantity format. Please enter a valid number.", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void showAlert(String title, String message, int messageType) {
        JFrame frame = new JFrame(title);
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }
}
