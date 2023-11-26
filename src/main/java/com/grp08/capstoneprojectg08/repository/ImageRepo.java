package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.ImageBase64;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Base64;

public class ImageRepo {
    private final MongoDatabase imageBase64DB = DatabaseConnection.getMongoDatabase("aims_image");

    // save image to database
    public boolean saveMediaImage(String filePath, String imageName){
        Document doc = new Document();
        String imageBase64 = ImageBase64.encodeImage(filePath);
        if(imageBase64 == null){
            System.err.println("Couldn't find aims_image database");
            return false;
        } else {
            try{
                doc.append("name", imageName);
                doc.append("stringBase64", imageBase64);
                assert imageBase64DB != null;
                imageBase64DB.getCollection("media_images").insertOne(doc);
                return true;
            } catch (Exception e){
                System.err.println("ImageRepo: " + e.getMessage());
                return false;
            }
        }
    }

    // get image from db and save to local storage if not exist
    public String getMediaImage(String imageName){
        Document doc = new Document();
        try{
            assert imageBase64DB != null;
            doc = imageBase64DB.getCollection("media_images").find(new Document("name", imageName)).first();
        } catch (Exception e){
            System.err.println("ImageRepo: " + e.getMessage());
        }
        if(doc == null){
            System.err.println("ImageRepo: Couldn't find image in database");
            return null;
        } else {
            String imageBase64 = doc.getString("stringBase64");
            String imagePath = "src/main/resources/com/grp08/capstoneprojectg08/assets/" + imageName;
            ImageBase64.decodeImage(imageBase64, imagePath);
            return imagePath;
        }
    }


}
