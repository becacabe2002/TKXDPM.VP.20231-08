package com.grp08.capstoneprojectg08.screen_handler.home;

import com.grp08.capstoneprojectg08.controller.EndpointRegister;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

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

    private final EndpointRegister endpointRegister = new EndpointRegister();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCategories();
        List<Media> mediaList = fetchMediaItems();

        loadMediaList(mediaList);
        searchBtn.setOnAction(event -> handleSearch());
    }

    private void initializeCategories() {
        MenuItem clearFilterItem = new MenuItem("Clear");
        MenuItem bookItem = new MenuItem(MediaCategory.Book.toString());
        MenuItem cdItem = new MenuItem(MediaCategory.CD.toString());
        MenuItem dvdItem = new MenuItem(MediaCategory.DVD.toString());

        clearFilterItem.setOnAction(event -> handleClearFilter());

        bookItem.setOnAction(event -> handleCategoryFilter(MediaCategory.Book));
        cdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.CD));
        dvdItem.setOnAction(event -> handleCategoryFilter(MediaCategory.DVD));

        categoryFilter.getItems().addAll(clearFilterItem, bookItem, cdItem, dvdItem);
    }

    private List<Media> fetchMediaItems() {
        List<Media> mediaList = new ArrayList<>();

        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setEndpoint("/home/medias");
        baseRequest.setMethod(RequestMethod.GET);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", "All");
        jsonObject.put("name", "");
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

    private Media createMediaFromJson(JSONObject mediaJson) {
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

    private void handleCategoryFilter(MediaCategory selectedCategory) {
        categoryFilter.setText(selectedCategory.toString());
        handleSearch();
    }

    private void handleSearch() {
        // Your implementation of handling search functionality
    }

    private void handleClearFilter() {
        // Your implementation of clearing filters
    }
}
