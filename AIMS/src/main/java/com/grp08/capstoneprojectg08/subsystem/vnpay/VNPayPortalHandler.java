package com.grp08.capstoneprojectg08.subsystem.vnpay;

import com.grp08.capstoneprojectg08.util.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


import java.io.IOException;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class VNPayPortalHandler implements Initializable {
    private String paymentUrl;

    private String redirectUrl;

    private UserSession userSession = UserSession.getInstance();

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private WebEngine engine;

    @FXML
    private Button btnBack;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = webDisplay.getEngine();
        engine.load(paymentUrl);
        engine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
                if(newLoc.contains("https://sandbox.vnpayment.vn/tryitnow/Home/VnPayReturn")){
                    userSession.setResultPaymentAddress(newLoc);
                }
            }
        });
    }

    public void displayDelayedScreen() {
        Duration delay = Duration.seconds(10);

        KeyFrame keyFrame = new KeyFrame(delay, event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/your_new_screen.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception accordingly
            }
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }
}

