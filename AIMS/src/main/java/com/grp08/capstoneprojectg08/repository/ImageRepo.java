package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
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
import java.util.Base64;
import java.util.Objects;

public interface ImageRepo extends BaseRepo{
    // save image to database
    public boolean saveMediaImage(String filePath, Media media);

    // get image from db and save to local storage if not exist
    public Image getMediaImage(Media media);
}
