package com.grp08.capstoneprojectg08.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class ImageBase64 {
    // TODO: encode image to base64
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

    // TODO: decode base64 to image
    public static void decodeImage(String base64String, String savePath){
        byte[] imageBytes = Base64.getDecoder().decode(base64String);
        try {
            Files.write(Paths.get(savePath), imageBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("ImageBase64: " + e.getMessage());
        }
    }
}
