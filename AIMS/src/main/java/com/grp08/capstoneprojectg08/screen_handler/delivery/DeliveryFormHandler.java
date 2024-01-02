package com.grp08.capstoneprojectg08.screen_handler.delivery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class DeliveryFormHandler {
    @FXML
    private Button cancelBtn;
    @FXML
    private CheckBox fastShipping;
    @FXML
    private Button nextBtn;

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
    private void handleFastShipping() {
        // Handle close button click event
        System.out.println("Fast shipping checkbox clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/rush-delivery-form.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
            Stage currentStage = (Stage) fastShipping.getScene().getWindow();
            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }

    @FXML
    private void handleNextButtonClick() {
        // Handle close button click event
        System.out.println("Cancel button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/payment-form.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
            Stage currentStage = (Stage) nextBtn.getScene().getWindow();
            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }



}
