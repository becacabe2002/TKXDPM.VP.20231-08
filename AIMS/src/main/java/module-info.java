module com.grp08.capstoneprojectg08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//    requires lombok;
    requires mysql.connector.j;
    requires javafx.web;
    requires mongo.java.driver;
    requires java.dotenv;
    requires org.json;
//    requires javax.servlet.api;


    opens com.grp08.capstoneprojectg08.screen_handler to javafx.fxml;
    opens com.grp08.capstoneprojectg08.screen_handler.cart to javafx.fxml;
    opens com.grp08.capstoneprojectg08.screen_handler.delivery to javafx.fxml;
    opens com.grp08.capstoneprojectg08.screen_handler.home to javafx.fxml;
    opens com.grp08.capstoneprojectg08.screen_handler.invoice to javafx.fxml;
    opens com.grp08.capstoneprojectg08.screen_handler.payment to javafx.fxml;
    opens com.grp08.capstoneprojectg08.screen_handler.product to javafx.fxml;
    opens com.grp08.capstoneprojectg08.subsystem to javafx.fxml;
    opens com.grp08.capstoneprojectg08.fxml to javafx.fxml;
    opens com.grp08.capstoneprojectg08.assets to javafx.fxml;
    opens com.grp08.capstoneprojectg08.assets.MediaImages to javafx.fxml;
    exports com.grp08.capstoneprojectg08 to javafx.graphics;
    exports com.grp08.capstoneprojectg08.screen_handler to javafx.fxml;
    exports com.grp08.capstoneprojectg08.subsystem to javafx.fxml;
    exports com.grp08.capstoneprojectg08.entity.cart;
    exports com.grp08.capstoneprojectg08.entity.delivery;
    exports com.grp08.capstoneprojectg08.entity.media;
    exports com.grp08.capstoneprojectg08.entity.order;
    exports com.grp08.capstoneprojectg08.entity.payment;
    exports com.grp08.capstoneprojectg08.entity.user;
    exports com.grp08.capstoneprojectg08.screen_handler.cart to javafx.fxml;
    exports com.grp08.capstoneprojectg08.screen_handler.delivery to javafx.fxml;
    exports com.grp08.capstoneprojectg08.screen_handler.home to javafx.fxml;
    exports com.grp08.capstoneprojectg08.screen_handler.invoice to javafx.fxml;
    exports com.grp08.capstoneprojectg08.screen_handler.payment to javafx.fxml;
    exports com.grp08.capstoneprojectg08.screen_handler.product to javafx.fxml;
}