package com.grp08.capstoneprojectg08.screen_handler.invoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceScreenHandler implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button confirmBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: display invoice detail
    }

    @FXML
    void handleBackButtonClick() {
        System.out.println("Back button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/delivery-form.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) backBtn.getScene().getWindow();
            currentStage.setScene(new Scene(root));

            // Show the home screen
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }

    }

    @FXML
    void handleConfirmButtonClick() {
        System.out.println("Confirm button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/payment-form.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current product detail stage
            Stage currentStage = (Stage) confirmBtn.getScene().getWindow();
            currentStage.close();

            // Show the home screen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }


    }

}
