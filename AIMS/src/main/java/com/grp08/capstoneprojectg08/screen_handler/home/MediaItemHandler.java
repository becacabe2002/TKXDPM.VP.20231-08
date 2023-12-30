package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
            if (this.media.getImageUrl() != null) {
                if (new File(this.media.getImageUrl()).exists()){
                    Image image = new Image(new File(this.media.getImageUrl()).toURI().toString());
                    MediaImage.setImage(image);
                }
            }
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
//        System.out.println("Add to Cart button clicked");
//
//        // Create a JSON object for the request body
//        JSONObject requestBody = new JSONObject();
//
//        // Retrieve the quantity from handleInputNumberChange() method
//        int quantity = handleInputNumberChange();
//
//        if (quantity != -1) {
//            try {
//                // Get the media ID from your Media object
//                Media media = new Media();
//                int mediaId = media.getID(); // Replace this with the actual way to get media ID
//
//                // Fill the JSON request body with media ID and quantity
//                requestBody.put("mediaId", mediaId);
//                requestBody.put("quantity", quantity);
//
//                // Create a BaseRequest for the endpoint
//                BaseRequest baseRequest = new BaseRequest(RequestMethod.POST, "/order/add-to-cart", requestBody);
//
//                // Use the EndpointRegister to handle the request
//                EndpointRegister endpointRegister = new EndpointRegister();
//                BaseResponse response = endpointRegister.handleRequest(baseRequest);
//
//                // Process the response as needed
//                if (response == null) {
//                    // Handle the case where the response is null
//                    System.out.println("No response received.");
//                } else {
//                    // Process the response as needed
//                    if (response.getResponseCode() == ResponseCode.OK) {
//                        // Handle success (e.g., display a success message)
//                        JFrame frame = new JFrame("JOptionPane showMessageDialog success");
//                        JOptionPane.showMessageDialog(frame,"Item added to cart successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
//                        System.out.println("Item added to cart successfully!");
//                    } else {
//                        JFrame frame = new JFrame("JOptionPane showMessageDialog success");
//                        JOptionPane.showMessageDialog(frame,"Failed to add item to cart.","Error", JOptionPane.ERROR_MESSAGE);
//                        // Handle failure (e.g., display an error message)
//                        System.out.println("Failed to add item to cart. Message: " + response.getResponseMessage());
//                    }
//                }
//
//                // Clear the input field after adding to cart
//                inputNumberTextField.clear();
//
//            } catch (NumberFormatException e) {
//                // Handle parsing errors or invalid input format
//                System.out.println("Invalid quantity format. Please enter a valid number.");
//                // Display an error message or perform error handling related to input format
//            }
//        } else {
//            // Handle a situation where the quantity is invalid from handleInputNumberChange()
//            System.out.println("Invalid quantity value.");
//        }
    }

    // TODO : toProductDetailBtn to see the detail of the media
    @FXML
    private void redirectToProductDetail() {
        if (media != null) {
            // TODO: Implement logic to navigate to the detailed view of the media item
            // For example:
            // Navigate to a new scene or view displaying detailed information about 'media'
            System.out.println("View Detail: " + media.getTitle());
        }
    }

    @FXML
    private int handleInputNumberChange() {
        // Handle input number change event
        String enteredNumber = inputNumberTextField.getText();
        int quantity = Integer.parseInt(enteredNumber);
        return quantity;
    }
}
