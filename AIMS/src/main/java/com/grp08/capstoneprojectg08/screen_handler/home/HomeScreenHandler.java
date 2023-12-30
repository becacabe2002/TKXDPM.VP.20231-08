package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.controller.HomeController;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
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
    private final EndpointRegister endpointRegister = new EndpointRegister();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize category filter options
        initializeCategories();
        List<Media> mediaList = new ArrayList<>();
        // Load and display a list of Media objects initially
//        loadMediaList(mediaList);

        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEndpoint("/home/medias");
        baseRequest.setMethod(RequestMethod.GET);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", "All");
        jsonObject.put("name", "");
        baseRequest.setBody(jsonObject);
        BaseResponse response = endpointRegister.handleRequest(baseRequest);
        JSONArray mediaArray = response.getBody().getJSONArray("mediaItems");
        for(int i=0; i<mediaArray.length();i++){
            JSONObject tempObject = mediaArray.getJSONObject(i);
            Media media = createMediaFromJson(tempObject);
            mediaList.add(media);
        }
        System.out.println(response.getResponseCode());
        System.out.println(response.getResponseMessage());
        System.out.println(response.getBody().getJSONArray("mediaItems"));

        // Add event handler for the Search button
//        searchBtn.setOnAction(event -> handleSearch());
        loadMediaList(mediaList);
    }

    private void initializeCategories() {
        // Create menu items for each category
        MenuItem clearFilterItem = new MenuItem("Clear");
        MenuItem bookItem = new MenuItem(MediaCategory.Book.toString());
        MenuItem cdItem = new MenuItem(MediaCategory.CD.toString());
        MenuItem dvdItem = new MenuItem(MediaCategory.DVD.toString());

        clearFilterItem.setOnAction(event -> handleClearFilter());

        // Set actions for each category
        bookItem.setOnAction(event -> handleCategoryFilter(MediaCategory.Book));
        cdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.CD));
        dvdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.DVD));

        // Add items to the SplitMenuButton
        categoryFilter.getItems().addAll(clearFilterItem,bookItem, cdItem, dvdItem);
    }

    private void handleCategoryFilter(MediaCategory selectedCategory) {
        // Set the selected category and trigger the search
        categoryFilter.setText(selectedCategory.toString());
        handleSearch();
    }

    //@FXML
    private void handleSearch() {
//        // Fetch the selected category and search name
//        MediaCategory selectedCategory = MediaCategory.valueOf(categoryFilter.getText());
//        String searchName = titleInputTf.getText().trim();
//
//        // Fetch the list of Media based on category and name
//        List<? extends Media> filteredMedia = homeController.getMediaByCategoryAndName(selectedCategory, searchName);
//
//        // Clear existing items from the GridPane
//        mediaListPane.getChildren().clear();
//
//        // Load and add media-item.fxml for the new list of filtered Media object to the GridPane
//        loadMediaList((List<Media>) filteredMedia);
//        String searchName = titleInputTf.getText().trim();
//        String category = categoryFilter.getText(); // Assuming this provides the selected category
//        BaseRequest request = new BaseRequest(); // Create a request object
//
//        try {
//            JSONObject body = new JSONObject();
//            body.put("category", category);
//            body.put("name", searchName);
//            request.setBody(body);
//
//            BaseResponse response = homeController.getFilterMedia(request);
//
//            if (response.getResponseCode() == ResponseCode.OK) {
//                JSONArray mediaItems = response.getBody().getJSONArray("mediaItems");
//                List<Media> filteredMedia = new ArrayList<>();
//
//                for (int i = 0; i < mediaItems.length(); i++) {
//                    JSONObject mediaJson = mediaItems.getJSONObject(i);
//                    Media media = createMediaFromJson(mediaJson); // Method to create Media object
//                    filteredMedia.add(media);
//                }
//
//                loadMediaList(filteredMedia);
//            } else {
//                // Handle other response codes if needed
//                System.out.println("Search failed: " + response.getResponseMessage());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private Media createMediaFromJson(JSONObject mediaJson) {
        Media media = new Media();
        try {
            media.setID(mediaJson.getInt("ID"));
            // Assuming MediaCategory is an enum type and get the category using a string
            MediaCategory category = MediaCategory.valueOf(mediaJson.getString("category"));
            media.setCategory(category);
            media.setPrice(mediaJson.getInt("price"));
            media.setStockQuantity(mediaJson.getInt("stockQuantity"));
            media.setTitle(mediaJson.getString("title"));
            media.setValue(mediaJson.getInt("value"));
            media.setImageUrl(mediaJson.getString("imageUrl"));
            media.setFastShipping(mediaJson.getBoolean("fastShipping"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return media;
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

    private void handleClearFilter() {
//        categoryFilter.setText("All"); // Set the default text
//
//        // Fetch all media without applying any filter
//        List<? extends Media> allMedia = homeController.getAllMedia();
//
//        // Clear existing items from the GridPane
//        mediaListPane.getChildren().clear();
//
//        // Load and add media-item.fxml for the new list of Media objects to the GridPane
//        loadMediaList((List<Media>) allMedia);
    }
}
