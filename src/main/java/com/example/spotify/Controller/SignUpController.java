package com.example.spotify.Controller;

import com.example.spotify.Model.User;
import com.example.spotify.SpotifyRegisterMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button DoSignUp;

    @FXML
    private Button SignIn;

    @FXML
    private Button SignUp;

    @FXML
    private TextField email;

    @FXML
    private TextField nickname;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label ErrorText;

    @FXML
    void doSignUp(ActionEvent event) {
        ErrorText.setText(User.addList(username.getText() , password.getText() , nickname.getText() , email.getText()).getMessage());
    }

    @FXML
    void signIn(ActionEvent event) {
        UpdateMenuController.toSignInMenu();
    }

    @FXML
    void signUp(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SignIn.setDisable(false);
        SignUp.setDisable(true);
    }
}
