package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.ImageBase64;

import com.grp08.capstoneprojectg08.util.StringProcess;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Base64;

public class ImageRepo {

    public ImageRepo() {
    }
    // save image to database
    public boolean saveMediaImage(String filePath, Media media){
        String imageName = StringProcess.fromNameToImageName(media);
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        assert mongoClient != null;
        MongoDatabase imageBase64DB = mongoClient.getDatabase("aims_image");
        Document doc = new Document();
        String imageBase64 = ImageBase64.encodeImage(filePath);
        if(imageBase64 == null){
            System.err.println("Couldn't find aims_image database");
            mongoClient.close();
            return false;
        } else {
            try{
                doc.append("name", imageName);
                doc.append("stringBase64", imageBase64);
                imageBase64DB.getCollection("media_images").insertOne(doc);
                mongoClient.close();
                return true;
            } catch (Exception e){
                System.err.println("ImageRepo: " + e.getMessage());
                mongoClient.close();
                return false;
            }
        }
    }

    // get image from db and save to local storage if not exist
    public String getMediaImage(Media media){
        String imageName = StringProcess.fromNameToImageName(media);
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        assert mongoClient != null;
        MongoDatabase imageBase64DB = mongoClient.getDatabase("aims_image");
        Document doc = new Document();
        try{
            doc = imageBase64DB.getCollection("media_images").find(new Document("name", imageName)).first();
        } catch (Exception e){
            System.err.println("ImageRepo: " + e.getMessage());
        }
        if(doc == null){
            System.err.println("ImageRepo: Couldn't find image in database");
            mongoClient.close();
            return null;
        } else {
            String imageBase64 = doc.getString("stringBase64");
            String imagePath = "src/main/resources/com/grp08/capstoneprojectg08/assets/MediaImages" + imageName;
            ImageBase64.decodeImage(imageBase64, imagePath);
            mongoClient.close();
            return imagePath;
        }
    }


}
