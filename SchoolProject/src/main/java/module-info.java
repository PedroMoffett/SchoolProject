module com.example.schoolproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.schoolproject to javafx.fxml;
    exports com.example.schoolproject;
    exports controller;
    opens controller to javafx.fxml;

    exports model;
    opens model to javafx.fxml;
}