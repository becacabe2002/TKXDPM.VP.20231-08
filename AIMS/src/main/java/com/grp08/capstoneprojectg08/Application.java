package com.grp08.capstoneprojectg08;

import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/home-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}