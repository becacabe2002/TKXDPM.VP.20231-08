package com.grp08.capstoneprojectg08;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.repository.MediaRepoImplement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class TestJson {
//    public static void main(String[] args) {
//        MediaRepo mediaRepo = new MediaRepoImplement();
//        List<Media> mediaList = new ArrayList<>();
//        mediaList.add(mediaRepo.findMediaById(1));
//        mediaList.add(mediaRepo.findMediaById(2));
//        mediaList.add(mediaRepo.findMediaById(3));
//        JSONArray jsonArray = new JSONArray();
//        for(Media media : mediaList){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("ID", media.getID());
//            jsonObject.put("category", media.getCategory());
//            jsonObject.put("price", media.getPrice());
//            jsonObject.put("stockQuantity", media.getStockQuantity());
//            jsonObject.put("title", media.getTitle());
//            jsonObject.put("value", media.getValue());
//            jsonObject.put("imageUrl", media.getImageUrl());
//            jsonObject.put("fastShipping", media.isFastShipping());
//            jsonArray.put(jsonObject);
//        }
//        JSONObject finalObject = new JSONObject();
//        finalObject.put("mediaList", jsonArray);
//        System.out.println(finalObject);
//
//        JSONObject parseObject = new JSONObject(finalObject.toString());
//        JSONArray parseArray = parseObject.getJSONArray("mediaList");
//        List<Media> parseMediaList = new ArrayList<>();
//        for(int i = 0; i < parseArray.length(); i++){
//            JSONObject jsonObject = parseArray.getJSONObject(i);
//            Media media = new Media(ID, category, price, stockQuantity, title, value, fastShipping);
//            media.setID(jsonObject.getInt("ID"));
//            media.setCategory(MediaCategory.valueOf(jsonObject.getString("category")));
//            media.setPrice(jsonObject.getInt("price"));
//            media.setStockQuantity(jsonObject.getInt("stockQuantity"));
//            media.setTitle(jsonObject.getString("title"));
//            media.setValue(jsonObject.getInt("value"));
//            media.setImageUrl(jsonObject.getString("imageUrl"));
//            media.setFastShipping(jsonObject.getBoolean("fastShipping"));
//            parseMediaList.add(media);
//        }
//        for(Media media : parseMediaList){
//            System.out.println("[");
//            System.out.println("\t" + media.getID());
//            System.out.println("\t" + media.getCategory());
//            System.out.println("\t" + media.getPrice());
//            System.out.println("\t" + media.getStockQuantity());
//            System.out.println("\t" + media.getTitle());
//            System.out.println("\t" + media.getValue());
//            System.out.println("\t" + media.getImageUrl());
//            System.out.println("\t" + media.isFastShipping());
//            System.out.println("];");
//        }
//    }
}
