package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.HomeController;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;


import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class HomeScreenHandler implements Initializable {


    @FXML
    private GridPane mediaListPane;

    @FXML
    private SplitMenuButton categoryFilter;

    @FXML
    private TextField titleInputTf;

    @FXML
    private Button searchBtn;

    private final HomeController homeController = new HomeController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize category filter options
        initializeCategories();

        // Load and display a list of Media objects initially
        loadMediaList(homeController.getMediaAndImage());

        // Add event handler for the Search button
        searchBtn.setOnAction(event -> handleSearch());
    }

    private void initializeCategories() {
        // Create menu items for each category
        MenuItem bookItem = new MenuItem(MediaCategory.Book.toString());
        MenuItem cdItem = new MenuItem(MediaCategory.CD.toString());
        MenuItem dvdItem = new MenuItem(MediaCategory.DVD.toString());

        // Set actions for each category
        bookItem.setOnAction(event -> handleCategoryFilter(MediaCategory.Book));
        cdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.CD));
        dvdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.DVD));

        // Add items to the SplitMenuButton
        categoryFilter.getItems().addAll(bookItem, cdItem, dvdItem);
    }

    private void handleCategoryFilter(MediaCategory selectedCategory) {
        // Set the selected category and trigger the search
        categoryFilter.setText(selectedCategory.toString());
        handleSearch();
    }

    private void handleSearch() {
        // Fetch the selected category and search name
        MediaCategory selectedCategory = MediaCategory.valueOf(categoryFilter.getText());
        String searchName = titleInputTf.getText().trim();

        // Fetch the list of Media based on category and name
        List<? extends Media> filteredMedia = homeController.getMediaByCategoryAndName(selectedCategory, searchName);

        // Clear existing items from the GridPane
        mediaListPane.getChildren().clear();

        // Load and add media-item.fxml for the new list of filtered Media object to the GridPane
        loadMediaList((List<Media>) filteredMedia);
    }

    private void loadMediaList(List<Media> mediaList) {
        int row = 0;
        int col = 0;
        for (Media media : mediaList) {
            try {
                // Load the media-item.fxml for each Media object
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/media-item.fxml"));
                Parent mediaItemRoot = loader.load();

                // Access the controller instance to set the Media object
                MediaItemHandler mediaItemController = loader.getController();
                mediaItemController.setMedia(media);

                // Add the loaded root node to the GridPane
                mediaListPane.add(mediaItemRoot, col, row);

            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }

            // Adjust the row and column
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    }
}
