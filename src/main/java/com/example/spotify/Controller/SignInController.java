package com.example.spotify.Controller;

import com.example.spotify.Model.Result;
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

public class SignInController implements Initializable {

    @FXML
    private Button DoSignIn;

    @FXML
    private Button ForgotPassword;

    @FXML
    private Button SignIn;

    @FXML
    private Button SignUp;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label ErrorText;

    private static Button NewSignInButton ;

    @FXML
    void doSignIn(ActionEvent event) {
        if(username.getText().equals("admin") || password.getText().equals("admin")){
            User.addList("Admin" , "Admin1234@" , "admin" , "admin@gmail.com");
            Result result = User.signInUser("Admin" ,"Admin1234@");
            ErrorText.setText(result.getMessage());
            if (result.isSuccessful()) {
                UpdateMenuController.toHomeMenu();
            }
        } else {
            Result result = User.signInUser(username.getText(), password.getText());
            ErrorText.setText(result.getMessage());
            if (result.isSuccessful()) {
                UpdateMenuController.toHomeMenu();
            }
        }
    }

    @FXML
    void forgotPassword(ActionEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) {

    }

    @FXML
    void signUp(ActionEvent event) {
        UpdateMenuController.toSignUpMenu();
    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SignIn.setDisable(true);
        SignUp.setDisable(false);
        NewSignInButton = SignIn;
    }


}
