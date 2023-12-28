package com.grp08.capstoneprojectg08;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class TestWebView extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(TestWebView.class.getResource("/com/grp08/capstoneprojectg08/fxml/web-page.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
