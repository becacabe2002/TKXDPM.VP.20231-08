package com.grp08.capstoneprojectg08.screen_handler.delivery;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.screen_handler.HandlerDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;



import java.io.IOException;

public class RushDeliveryFormHandler {
    private EndpointRegister endpointRegister = new EndpointRegister();
    @FXML
    private Button cancelBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private void handleCancelButtonClick() {
        System.out.println("Cancel button clicked");
        Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
        HandlerDto.getInstance().getRunningDeliveryFormHandler().fastShipping.setSelected(false);
        currentStage.close();
    }

    @FXML
    private void handleUpdateButtonClick(){
        System.out.println("Cancel button clicked");
        Stage currentStage = (Stage) updateBtn.getScene().getWindow();
        currentStage.close();
        showAlert("Shipping Fee Updated", "Additional fee for rush shipping has been added to the invoice.", Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
