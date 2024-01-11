package com.grp08.capstoneprojectg08.screen_handler.cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class CartItemHandler implements Initializable{
    private CartItem cartItem;

    @FXML
    private Label titleLabel;

    @FXML
    private Label categoryLb;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField quantityText;

    @FXML
    private Label totalLabel;

    @FXML
    private ImageView cartItemAva;

    private EndpointRegister endpointRegister = new EndpointRegister();

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
        initializeCartItem();
    }
    public CartItem getCartItem() {
        return cartItem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCartItem();
        quantityText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER){
                    handleInputNumberChange();
                }
            }
        });
    }

    // Method to set data for each media item in the cart
    public void initializeCartItem(){
        if(this.cartItem != null){
            Media media = cartItem.getMedia();
            titleLabel.setText(media.getTitle());
            categoryLb.setText(media.getCategory().toString());
            priceLabel.setText(String.valueOf(media.getPrice()) + "$");
            quantityText.setText(String.valueOf(cartItem.getQuantity()));
            totalLabel.setText(String.valueOf(cartItem.getSubPrice()) + "$");
            cartItemAva.setImage(ImageUtil.getMediaImage(this.cartItem.getMedia()));
        }
    }

    private void handleInputNumberChange() {
        String enteredNumber = quantityText.getText();
        try {
            int quantity = Integer.parseInt(enteredNumber);
            int numChange = quantity - cartItem.getQuantity();
            if (quantity > 0) {
                handleQuantityChange(numChange);
            } else {
                showAlert("Error", "Quantity should be a positive value.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid quantity format. Please enter a valid number.", Alert.AlertType.ERROR);
        }
    }

    public void handleQuantityChange(int quantity){
        BaseRequest request = new BaseRequest();
        request.setMethod(RequestMethod.PUT);
        JSONObject reqBody = new JSONObject();
        reqBody.put("mediaId", cartItem.getMediaId());
        reqBody.put("change", quantity);
        request.setEndpoint("/cart/change-item-quantity");
        request.setBody(reqBody);
        BaseResponse res = endpointRegister.handleRequest(request);
        switch (res.getResponseCode()){
            case ResponseCode.OK -> showAlert("Success", res.getResponseMessage(), Alert.AlertType.INFORMATION);
            case ResponseCode.BAD_REQUEST -> showAlert("Error", res.getResponseMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void increaseItemIconClick(){
        handleQuantityChange(1);
    }

    @FXML
    public void decreaseItemIconClick(){
        handleQuantityChange(-1);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setOnCloseRequest(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/view-cart-screen.fxml"));
                Parent root = loader.load();

                // Create a new stage for the home screen
                Stage stage = (Stage) quantityText.getScene().getWindow();
                stage.setScene(new Scene(root));

                // Show the home screen
                stage.show();
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        });
        alert.showAndWait();
    }

}
