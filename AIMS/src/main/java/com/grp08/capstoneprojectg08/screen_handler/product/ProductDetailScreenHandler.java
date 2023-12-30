package com.grp08.capstoneprojectg08.screen_handler.product;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

    // Other fields and methods...

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code can go here if needed
    }

    @FXML
    private void handleCloseButtonClick() {
        // Handle close button click event
        System.out.println("Close button clicked");
        // Add logic to close the product details screen or perform other actions
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current stage (product detail screen)
            Stage currentStage = (Stage) closeButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
            }
        }

    @FXML
    private void handleAddToCartButtonClick() {
//        // Handle add to cart button click event
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
//                Media media = new Media(ID, category, price, stockQuantity, title, value, fastShipping); // Replace this with the actual Media object creation
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



    @FXML
    private int handleInputNumberChange() {
        // Handle input number change event
        String enteredNumber = inputNumberTextField.getText();
        int quantity = Integer.parseInt(enteredNumber);
        return quantity;
    }
        // Add logic to process the entered number (e.g., validation, parsing
}