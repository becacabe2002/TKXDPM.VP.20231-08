package com.grp08.capstoneprojectg08.screen_handler.delivery;

import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryFormHandler implements Initializable {
    @FXML
    private Button cancelBtn;
    @FXML
    public CheckBox fastShipping;
    @FXML
    private Button nextBtn;
    @FXML
    private TextField name;
    @FXML
    private TextField phoneNum;
    @FXML
    private SplitMenuButton city;
    @FXML
    private TextField detailAddress;
    @FXML
    private TextField instruction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuItem HanoiItem = createCategoryMenuItem("Hà Nội");
        MenuItem DaNangItem = createCategoryMenuItem("Đà Nẵng");
        MenuItem HaiPhongItem = createCategoryMenuItem("Hải Phòng");
        MenuItem LaoCaiItem = createCategoryMenuItem("Lào Cai");

        city.getItems().addAll(HanoiItem, DaNangItem, HaiPhongItem, LaoCaiItem);
    }
    private MenuItem createCategoryMenuItem(String str) {
        MenuItem categoryItem = new MenuItem(str);
        return categoryItem;
    }

    @FXML
    private void handleCancelButtonClick() {
        // Handle close button click event
        System.out.println("Cancel button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
            Parent root = loader.load();

            // Create a new stage for the home screen
            Stage stage = (Stage) fastShipping.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            // Handle the exception accordingly
        }
    }

    @FXML
    private void handleFastShipping() {
        if (!fastShipping.isSelected()) {
            System.out.println("Fast shipping box is checked.");
            try {
                // Load the FXML file for the home screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/rush-delivery-form.fxml"));
                Parent root = loader.load();

                // Create a new stage for the home screen
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                // Handle the exception accordingly
            }
        } else{
            System.out.println("Fast shipping box is unchecked.");
        }
    }

    @FXML
    private void handleNextButtonClick() {
        // Handle close button click event
        System.out.println("Cancel button clicked");

        try {
            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/invoice-screen.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) nextBtn.getScene().getWindow();
            currentStage.setScene(new Scene(root));

            // Show the home screen
            currentStage.show();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            // Handle the exception accordingly
        }
    }
    // validate input: name, phone, address, shipping instructions, (shipping time and Fast shipping instructions)
    private boolean validateInputName(String name){
        if (name.trim().isEmpty()){
            return false;
        } else return true;
    }

    private boolean validateInputPhone(String phone){
        boolean check = true;
        if(phone.length() != 10) check = false;
        if (!phone.startsWith("0")) check = false;
        try{
            Integer.parseInt(phone);
        } catch (NumberFormatException e){
            check = false;
        }
        return check;
    }

    private boolean validateInputAddress(String address){
        if(address.trim().isEmpty()){
            return false;
        } else return true;
    }

    private boolean validateInputShippingInstructions(String shippingInstructions){
        if(shippingInstructions.trim().isEmpty()){
            return false;
        } else return true;
    }

    private boolean validateInputShippingForm(String name, String phone, String address, String shippingInstructions){
        return validateInputName(name)
                && validateInputPhone(phone)
                && validateInputAddress(address)
                && validateInputShippingInstructions(shippingInstructions);
    }


}
