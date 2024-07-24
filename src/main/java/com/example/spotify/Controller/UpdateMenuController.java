package com.example.spotify.Controller;

import com.example.spotify.SpotifyRegisterMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateMenuController {
    private static List<String> pagePath = new ArrayList<>();
    public static void toSignInMenu(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("SignIn-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("SignInMenu");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void toSignUpMenu(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("SignUp-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("SignUpMenu");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void toHomeMenu(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("HomeMenu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("HomeMenu");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
            formingPagePath("HomeMenu-view.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void toProfileMenu(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ProfileMenu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("ProfileMenu");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
            formingPagePath("ProfileMenu-view.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void toArtistPage(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ArtistPage-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("ArtistPage");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
            formingPagePath("ArtistPage-view.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void toGenrePage(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("GenrePage-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("GenrePage");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
            formingPagePath("GenrePage-view.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void toSongPage(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("SongPage-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle("SongPage");
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
            formingPagePath("SongPage-view.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void back(){
        try{
            for(int i=0 ; i< pagePath.size() ;i++)
                System.out.println(pagePath.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader(SpotifyRegisterMenu.class.getResource(pagePath.get(pagePath.size()-2)));
            Scene scene = new Scene(fxmlLoader.load());
            SpotifyRegisterMenu.stage.setTitle(pagePath.get(pagePath.size()-2).replaceAll("-view.fxml" , ""));
            SpotifyRegisterMenu.stage.setScene(scene);
            SpotifyRegisterMenu.stage.show();
            pagePath.remove(pagePath.size()-1);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void formingPagePath(String path){
        pagePath.add(path) ;
        for(int i=0 ; i< pagePath.size() ; i++){
            if(pagePath.get(i).equals(path)){
                while(pagePath.size()>i+1){
                    pagePath.remove(pagePath.size()-1);
                }
                break;
            }
        }
    }
}
