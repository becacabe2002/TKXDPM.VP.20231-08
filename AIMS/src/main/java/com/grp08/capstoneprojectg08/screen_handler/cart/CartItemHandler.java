package com.grp08.capstoneprojectg08.screen_handler.cart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

public class CartItemHandler {
    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityText;

    @FXML
    private Label totalLabel;

    // Method to set data for each media item in the cart
    public void setMediaItemData(Media mediaItem) {
//        JSONObject responseBody = response.getBody();
//        JSONObject mediaDetails = responseBody.getJSONObject("media");
        titleLabel.setText(mediaItem.getTitle());
//        authorLabel.setText(mediaItem.getAuthor());
        priceLabel.setText(String.valueOf(mediaItem.getPrice()));
        quantityText.setText(String.valueOf(handleInputNumberChange()));
        totalLabel.setText(String.valueOf(mediaItem.getValue()));
        // Set other properties of the media item in the UI components
    }

    @FXML
    private int handleInputNumberChange() {
        String enteredNumber = quantityText.getText();
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

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
