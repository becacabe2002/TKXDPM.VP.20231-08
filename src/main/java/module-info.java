module com.grp08.capstoneprojectg08 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.grp08.capstoneprojectg08 to javafx.fxml;
    exports com.grp08.capstoneprojectg08;
}