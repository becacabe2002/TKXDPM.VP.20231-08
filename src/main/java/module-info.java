module com.grp08.capstoneprojectg08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.grp08.capstoneprojectg08 to javafx.fxml;
    exports com.grp08.capstoneprojectg08;
}