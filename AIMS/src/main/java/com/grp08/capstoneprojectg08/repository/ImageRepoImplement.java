package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.ImageBase64;
import com.grp08.capstoneprojectg08.util.StringProcess;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.image.Image;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ImageRepoImplement implements ImageRepo{
    private static PreparedStatement ppStatement = null;
    private static Dotenv dotenv = Dotenv.load();

    @Override
    public boolean saveMediaImage(String filePath, Media media){// if media Image URL is null, update it with image path
        String imageName = StringProcess.fromNameToImageName(media);
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        assert mongoClient != null;
        MongoDatabase aimsDB = mongoClient.getDatabase(dotenv.get("MONGO_DB"));
        Document doc = new Document();
        String imageBase64 = ImageBase64.encodeImage(filePath);
        if(imageBase64 == null){
            System.err.println("Couldn't find aims_2023 database");
            mongoClient.close();
            return false;
        } else {
            try{
                doc.append("name", imageName);
                doc.append("stringBase64", imageBase64);
                aimsDB.getCollection("media_images").insertOne(doc);
                mongoClient.close();
                String updateScript = "update Media set imageUrl = ? where title = ?;";
                try{
                    ppStatement = Objects.requireNonNull(DatabaseConnection.getConnectionMySQL()).prepareStatement(updateScript);
                    ppStatement.setString(1, "target/classes/com/grp08/capstoneprojectg08/assets/MediaImages/" + imageName);
                    ppStatement.setString(2, media.getTitle());
                    ppStatement.executeUpdate();
                } catch (SQLException e){
                    System.err.println("MediaRepo: " + e.getMessage());
                }
                return true;
            } catch (Exception e){
                System.err.println("ImageRepo: " + e.getMessage());
                mongoClient.close();
                return false;
            }
        }
    }

    // get image from db and save to local storage if not exist
    @Override
    public Image getMediaImage(Media media){
        String imageName = StringProcess.fromNameToImageName(media);
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        assert mongoClient != null;
        MongoDatabase imageBase64DB = mongoClient.getDatabase(dotenv.get("MONGO_DB"));
        Document doc = null;
        try{
            doc = imageBase64DB.getCollection("media_images").find(Filters.eq("name", imageName)).first();
        } catch (Exception e){
            System.err.println("ImageRepo: " + e.getMessage());
        }
        if(doc == null){
            System.err.println("ImageRepo: Couldn't find image in database");
            mongoClient.close();
            return null;
        } else {
            String imageBase64 = doc.getString("stringBase64");
            mongoClient.close();
            return ImageBase64.fromBase64toJavaFXImage(imageBase64);
        }
    }
}
