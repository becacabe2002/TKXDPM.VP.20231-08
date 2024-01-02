package com.grp08.capstoneprojectg08.screen_handler.delivery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;



import java.io.IOException;

public class RushDeliveryFormHandler {
    @FXML
    private Button cancelBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private void handleCancelButtonClick() {
        // Handle close button click event
        System.out.println("Cancel button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
            Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }
    @FXML
    private void handleUpdateButtonClick(){
        System.out.println("Cancel button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/delivery-form.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
            Stage currentStage = (Stage) updateBtn.getScene().getWindow();
            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }

}
