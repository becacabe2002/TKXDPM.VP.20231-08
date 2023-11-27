module com.grp08.capstoneprojectg08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//    requires lombok;
    requires mysql.connector.j;
    requires javafx.web;
    requires mongo.java.driver;
//    requires javax.servlet.api;


    opens com.grp08.capstoneprojectg08.screen_handler to javafx.fxml;
    opens com.grp08.capstoneprojectg08.subsystem to javafx.fxml;
    exports com.grp08.capstoneprojectg08 to javafx.graphics;
    exports com.grp08.capstoneprojectg08.screen_handler to javafx.fxml;
    exports com.grp08.capstoneprojectg08.subsystem to javafx.fxml;
}