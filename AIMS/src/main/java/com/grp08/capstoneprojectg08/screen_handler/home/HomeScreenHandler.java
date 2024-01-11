package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    public Label NumberItemInCartLabel;


    private final EndpointRegister endpointRegister = new EndpointRegister();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCategories();
        List<Media> mediaList = fetchMediaItems(null, "All");
        loadMediaList(mediaList);
        updateNumberOfCartItems();
        searchBtn.setOnAction(event -> handleSearch());
    }

    private void initializeCategories() {
//        MenuItem bookItem = new MenuItem(MediaCategory.Book.toString());
//        MenuItem cdItem = new MenuItem(MediaCategory.CD.toString());
//        MenuItem dvdItem = new MenuItem(MediaCategory.DVD.toString());

//        bookItem.setOnAction(event -> handleCategoryFilter(MediaCategory.Book));
//        cdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.CD));
//        dvdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.DVD));
        MenuItem bookItem = createCategoryMenuItem(MediaCategory.Book);
        MenuItem cdItem = createCategoryMenuItem(MediaCategory.CD);
        MenuItem dvdItem = createCategoryMenuItem(MediaCategory.DVD);
        MenuItem allItem = createCategoryMenuItem(MediaCategory.All);

        categoryFilter.getItems().addAll(allItem, bookItem, cdItem, dvdItem);
    }

    private void updateNumberOfCartItems(){
        BaseRequest request = new BaseRequest();
        request.setMethod(RequestMethod.GET);
        request.setEndpoint("/cart/info");
        BaseResponse res = endpointRegister.handleRequest(request);
        JSONObject body = res.getBody();
        int numberItems = body.getInt("numberOfItems");
        NumberItemInCartLabel.setText(String.valueOf(numberItems));
    }

    private List<Media> fetchMediaItems(String searchname, String category) {
        List<Media> mediaList = new ArrayList<>();

        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEndpoint("/home/medias");
        baseRequest.setMethod(RequestMethod.GET);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", category);
        if (searchname != null && !searchname.isEmpty()) {
            jsonObject.put("name", searchname);
        } else {
            jsonObject.put("name", "");
        }
        baseRequest.setBody(jsonObject);

        BaseResponse response = endpointRegister.handleRequest(baseRequest);
        if (response.getResponseCode() == ResponseCode.OK) {
            JSONArray mediaArray = response.getBody().getJSONArray("mediaItems");
            for (int i = 0; i < mediaArray.length(); i++) {
                JSONObject tempObject = mediaArray.getJSONObject(i);
                Media media = createMediaFromJson(tempObject);
                mediaList.add(media);
            }
        } else {
            System.out.println("Failed to fetch media items: " + response.getResponseMessage());
        }
        return mediaList;
    }

    public Media createMediaFromJson(JSONObject mediaJson) {
        // Your implementation of creating Media from JSON
        // Ensure proper exception handling and object creation
//        return null; // Replace null with your Media object
        int ID = mediaJson.getInt("ID");
        MediaCategory category = mediaJson.getEnum(MediaCategory.class,"category");
        int price = mediaJson.getInt("price");
        int stockQuantity = mediaJson.getInt("stockQuantity");
        String title = mediaJson.getString("title");
        int value = mediaJson.getInt("value");
        String imageUrl = mediaJson.getString("imageUrl");
        boolean fastShipping = mediaJson.getBoolean("fastShipping");

        return new Media(ID, category, price, stockQuantity, title, value, imageUrl, fastShipping);
    }

    private void loadMediaList(List<Media> mediaList) {
        int row = 0;
        int col = 0;
        for (Media media : mediaList) {
            try {
                long startTime = System.currentTimeMillis();
                // Load the media-item.fxml for each Media object
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/media-item.fxml"));
                Parent mediaItemRoot = loader.load();
                System.out.println("Media item loaded successfully!");

                // Access the controller instance to set the Media object
                MediaItemHandler mediaItemController = loader.getController();
                mediaItemController.setMedia(media);

                // Add the loaded root node to the GridPane
                mediaListPane.add(mediaItemRoot, col, row);
                long endTime = System.currentTimeMillis();
                System.out.println("Time taken to load media item: " + (endTime - startTime));
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

    private void handleCategoryFilter(MediaCategory selectedCategory) {
        categoryFilter.setText(selectedCategory.toString());
        handleSearch();
        if (selectedCategory == MediaCategory.All) {
            handleFilterAll();
        } else {
            List<Media> filteredItems = fetchFilteredMediaItems(selectedCategory);
            loadMediaList(filteredItems);
        }
    }

    private List<Media> fetchFilteredMediaItems(MediaCategory selectedCategory) {
        // Implement logic to fetch media items based on the selected category
        // For instance, make a request to fetch items related to the selected category
        // Replace this with your actual logic to filter media items based on the category
        List<Media> allMediaItems = fetchMediaItems(null, "All");
        List<Media> filteredItems = new ArrayList<>();
        for (Media media : allMediaItems) {
            if (media.getCategory() == selectedCategory) {
                filteredItems.add(media);
            }
        }
        return filteredItems;
    }

    @FXML
    private void handleSearch() {
        String searchText = titleInputTf.getText().toLowerCase();

        // Perform search operation based on the searchText
        String category = categoryFilter.getText();
        List<Media> allMediaItems = fetchMediaItems(searchText, category);

        if (!searchText.isEmpty()) {
            List<Media> searchResults = new ArrayList<>();

            for (Media media : allMediaItems) {
                String title = media.getTitle().toLowerCase();
                if (title.contains(searchText)) {
                    searchResults.add(media);
                }
            }

            displayMediaList(searchResults); // Display search results in mediaListPane
        } else {
            // If the search field is empty, display all available media items
            displayMediaList(allMediaItems);
        }
    }

    private void displayMediaList(List<Media> mediaList) {
        // Clear mediaListPane before adding media items
        mediaListPane.getChildren().clear();

        // Load and display media items in mediaListPane
        int row = 0;
        int col = 0;
        for (Media media : mediaList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/media-item.fxml"));
                Parent mediaItemRoot = loader.load();

                MediaItemHandler mediaItemController = loader.getController();
                mediaItemController.setMedia(media);

                mediaListPane.add(mediaItemRoot, col, row);
            } catch (IOException e) {
                e.printStackTrace();
            }

            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    }

    private void handleFilterAll() {
        // Your implementation of clearing filters
        List<Media> allMediaItems = fetchMediaItems(null, "All"); // Implement this method to fetch all media items
        loadMediaList(allMediaItems); // Load all media items in the UI
    }

    private MenuItem createCategoryMenuItem(MediaCategory category) {
        MenuItem categoryItem = new MenuItem(category.toString());
        categoryItem.setOnAction(event -> handleCategoryFilter(category));
        return categoryItem;
    }

    @FXML
    private void handleCartIconClick(MouseEvent event) {
        try {
            // Load the FXML file for the view cart screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/view-cart-screen.fxml"));
            Parent viewCartRoot = loader.load();

            // Create a new stage for the view cart screen
            Stage currentStage = (Stage) mediaListPane.getScene().getWindow();
            currentStage.setTitle("View Cart");
            currentStage.setScene(new Scene(viewCartRoot));

            // Show the view cart screen
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
