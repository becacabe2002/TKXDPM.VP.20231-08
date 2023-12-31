package com.grp08.capstoneprojectg08.screen_handler.product;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.ImageUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import javax.swing.*;

public class ProductDetailScreenHandler implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Button closeButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private TextField inputNumberTextField;


    @FXML
    private Label author;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code can go here if needed

    }

    public void setMedia(Media media) {
        this.media = media;
        initializeMedia();
    }

    private void initializeMedia() {
        if (this.media != null) {
            name.setText(this.media.getTitle());
            author.setText(this.media.getCategory().toString());
            price.setText(this.media.getPrice() + " $");
            stockQuantity.setText(String.valueOf(this.media.getStockQuantity()));

            // Initialize ImageView with the image URL
            Image image = ImageUtil.getMediaImage(this.media);
            imageUrl.setImage(image);
        }
    }

    public Media getMedia() {
        return media;
    }
    @FXML
    private void handleCloseButtonClick() {
        // Handle close button click event
        System.out.println("Close button clicked");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Get the current stage (product detail screen)
            Stage currentStage = (Stage) mainAnchorPane.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
        }

    @FXML
    private void handleAddToCartButtonClick() {
        System.out.println("Add to Cart button clicked");

        int quantity = handleInputNumberChange();

        if (quantity > 0 && media != null) {
            try {
                int mediaId = media.getID(); // Replace this with the actual way to get media ID

                JSONObject requestBody = new JSONObject();
                requestBody.put("mediaId", mediaId);
                requestBody.put("quantity", quantity);

                BaseRequest baseRequest = new BaseRequest(RequestMethod.POST, "/order/add-to-cart", requestBody);

                EndpointRegister endpointRegister = new EndpointRegister();
                BaseResponse response = endpointRegister.handleRequest(baseRequest);

                if (response != null && response.getResponseCode() == ResponseCode.OK) {
                    System.out.println("Item added to cart successfully!");
                    // Update UI or show a success message
                    showAlert("Success", "Item added to cart successfully!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("Failed to add item to cart. Message: " + response.getResponseMessage());
                    // Update UI or show an error message
                    showAlert("Error", "Failed to add item to cart.", JOptionPane.ERROR_MESSAGE);
                }

                inputNumberTextField.clear();
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format. Please enter a valid number.");
                // Handle the error: show an error message to the user
                showAlert("Error", "Invalid quantity format. Please enter a valid number.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Invalid quantity value or no media selected.");
            // Handle the error: show an error message to the user
            showAlert("Error", "Invalid quantity value or no media selected.", JOptionPane.ERROR_MESSAGE);
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

    // Method to show a dialog box based on the JOptionPane
    private void showAlert(String title, String message, int messageType) {
        JFrame frame = new JFrame(title);
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }
        // Add logic to process the entered number (e.g., validation, parsing
}