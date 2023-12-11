package com.grp08.capstoneprojectg08.util;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class ImageBase64 {
    public static String encodeImage(String imagePath) {
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            System.err.println("ImageBase64: " + e.getMessage());
            return null;
        }
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static void decodeImage(String base64String, String savePath){
            byte[] imageBytes = Base64.getDecoder().decode(base64String);
        try {
            Files.write(Paths.get(savePath), imageBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("ImageBase64: " + e.getMessage());
        }
    }

    public static Image fromBase64toJavaFXImage(String base64String){
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        ByteArrayInputStream outputStream = new ByteArrayInputStream(imageBytes);
        return new Image(outputStream);
    }
}
