package com.grp08.capstoneprojectg08.screen_handler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccessScreenHandler {

    @FXML
    private Button backBtn;

    @FXML
    void handleBackButtonClick() {
        System.out.println("Back button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
            Stage currentStage = (Stage) backBtn.getScene().getWindow();
            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }

    }

}
