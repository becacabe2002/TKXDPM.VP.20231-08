package com.grp08.capstoneprojectg08.subsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {
    private URL url;
    private WebEngine engine;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnHome;

    @FXML
    private WebView webDisplay;

    @FXML
    void goBack(ActionEvent event) {
        WebHistory history = engine.getHistory();
        if(history.getCurrentIndex() > 0){
            history.go(-1);
        }
        engine.reload();
    }

    @FXML
    void goHome(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = webDisplay.getEngine();
//        try {
//            url = new URL("https://www.twitter.com");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
        engine.load(url.toString());
    }

}

