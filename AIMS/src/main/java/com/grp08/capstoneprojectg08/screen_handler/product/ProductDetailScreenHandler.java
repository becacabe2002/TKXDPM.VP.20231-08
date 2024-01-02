package com.grp08.capstoneprojectg08.screen_handler.product;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.service.MediaService;
import com.grp08.capstoneprojectg08.util.ImageUtil;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.json.JSONObject;

import javax.swing.*;

public class ProductDetailScreenHandler implements Initializable {

//    @FXML
//    private AnchorPane mainAnchorPane;
    private EndpointRegister endpointRegister = new EndpointRegister();

    @FXML
    private Button closeButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private TextField inputNumberTextField;


    @FXML
    private Label categoryLb;

    @FXML
    private TextArea detail;

    @FXML
    private ImageView imageUrl;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Text stockQuantity;



    // Other fields and methods...
    private Media media;

    private String information;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media, String information) {
        this.media = media;
        this.information = information;
        loadMediaDetails();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMediaDetails();
        //addToCartButton.setOnAction(event -> handleAddToCartButtonClick());
    }

    public void loadMediaDetails() {
        if(media != null){
            name.setText(media.getTitle());
            categoryLb.setText(media.getCategory().toString());
            detail.setText(information);
            price.setText(String.valueOf(media.getPrice()));
            stockQuantity.setText(String.valueOf(media.getStockQuantity()));
            Image image = ImageUtil.getMediaImage(media);
            imageUrl.setImage(image);
        }
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
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }

    @FXML
    private void handleAddToCartButtonClick() {
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

    @FXML
    private int handleInputNumberChange() {
        String enteredNumber = inputNumberTextField.getText();
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

    // Method to show a dialog box based on the JOptionPane
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        // Add logic to process the entered number (e.g., validation, parsing
}