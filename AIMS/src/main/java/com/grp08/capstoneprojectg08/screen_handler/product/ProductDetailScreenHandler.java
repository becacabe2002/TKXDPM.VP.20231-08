package com.grp08.capstoneprojectg08.screen_handler.product;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        // Handle add to cart button click event
        System.out.println("Add to Cart button clicked");
        // Add logic to add the item to the cart or perform other actions
    }

    @FXML
    private void handleInputNumberChange() {
        // Handle input number change event
        String enteredNumber = inputNumberTextField.getText();
        System.out.println("Entered number: " + enteredNumber);
        // Add logic to process the entered number (e.g., validation, parsing)
    }
}