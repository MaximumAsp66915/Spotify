module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.spotify to javafx.fxml;
    exports com.example.spotify;
    exports com.example.spotify.Controller;
    opens com.example.spotify.Controller to javafx.fxml;
}