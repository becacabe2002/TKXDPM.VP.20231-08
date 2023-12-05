package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.entity.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import javafx.scene.image.Image;
import javafx.scene.text.Text;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class MediaItemHandler implements Initializable {

    @FXML
    private Button addToCartBtn;

    @FXML
    private Button toProductDetailBtn;

    @FXML
    private ImageView MediaImage;



    @FXML
    private Label mediaTitleLb;

    @FXML
    private Label mediaCategoryLb;

    @FXML
    private Label mediaPriceLb;

    @FXML
    private Text mediaStockLb;

//    @FXML
//    private Label stockLabel;

    @FXML
    private TextField cartitemQuantityInput;

    private Media media;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the UI components with the provided media object
        initializeMedia();
    }

    private void initializeMedia() {
        if (this.media != null) {
            mediaTitleLb.setText(this.media.getTitle());
            mediaCategoryLb.setText(this.media.getCategory().toString());
            mediaPriceLb.setText(this.media.getPrice() + " $");
            mediaStockLb.setText(String.valueOf(this.media.getStockQuantity()));

            // Initialize ImageView with the image URL
            if (this.media.getImageUrl() != null) {
                if (new File(this.media.getImageUrl()).exists()){
                    Image image = new Image(new File(this.media.getImageUrl()).toURI().toString());
                    MediaImage.setImage(image);
                }
            }
        }
    }

    public void setMedia(Media media) {
        this.media = media;
        initializeMedia();
    }

    public Media getMedia() {
        return media;
    }


    //TODO : addToCartBtn to add media to cart

    // TODO : toProductDetailBtn to see the detail of the media
}
