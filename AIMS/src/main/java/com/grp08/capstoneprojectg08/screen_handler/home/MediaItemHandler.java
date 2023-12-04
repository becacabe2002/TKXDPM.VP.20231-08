package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

import javafx.fxml.Initializable;



import com.grp08.capstoneprojectg08.controller.HomeController;

import java.net.URL;
import java.util.ResourceBundle;


public class MediaItemHandler implements  Initializable {
    @FXML
    private Button addToCartBtn;

    @FXML
    private Button toProductDetailBtn;

    @FXML
    private ImageView mediaImage;

    @FXML
    private VBox vBox;

    @FXML
    private Label mediaTitleLb;

    @FXML
    private Label mediaCategoryLb;

    @FXML
    private Label mediaPriceLb;

    @FXML
    private Label mediaStockLb;

    @FXML
    private Label stockLabel;

    @FXML
    private TextField cartitemQuantityInput;

    private Media media;

    public void setMedia(Media media) {
        this.media = media;// Reinitialize the UI with the new Media object
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (media != null) {
            mediaTitleLb.setText(media.getTitle());
            mediaCategoryLb.setText(media.getCategory().toString());
            mediaPriceLb.setText(String.valueOf(media.getPrice()));
            mediaStockLb.setText(String.valueOf(media.getStockQuantity()));

            // Initialize ImageView with the image URL
            if (media.getImageUrl() != null && !media.getImageUrl().isEmpty()) {
                Image image = new Image(media.getImageUrl());
                mediaImage.setImage(image);
            }


        }
    }


    //TODO : addToCartBtn to add media to cart

    // TODO : toProductDetailBtn to see the detail of the media
}
