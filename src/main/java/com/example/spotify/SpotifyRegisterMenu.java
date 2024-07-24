package com.example.spotify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SpotifyRegisterMenu extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage ;
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("SignIn-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SignInMenu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}