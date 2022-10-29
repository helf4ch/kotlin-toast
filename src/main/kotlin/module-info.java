module com.app.toastapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires javafx.media;


    opens com.app.toastapp to javafx.fxml;
    exports com.app.toastapp;
}