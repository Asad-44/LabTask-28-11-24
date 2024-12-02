module com.example.labtask281124 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.labtask281124 to javafx.fxml;
    exports com.example.labtask281124;
}