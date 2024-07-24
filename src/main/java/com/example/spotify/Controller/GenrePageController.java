package com.example.spotify.Controller;

import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Genre;
import com.example.spotify.Model.Song;
import com.example.spotify.SpotifyRegisterMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.spotify.Model.Artist.artistList;

public class GenrePageController implements Initializable {

    @FXML
    private HBox ArtistContainer;

    @FXML
    private Label GenreDescription;

    @FXML
    private ImageView GenreImage;

    @FXML
    private Label GenreLabel;

    @FXML
    private VBox SongContainer;

    public static Genre genre;

    @FXML
    void back(ActionEvent event) {
        UpdateMenuController.back();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GenreLabel.setText(genre.name);
        GenreImage.setImage(new Image(genre.image));

        try {
            for (int i=0 ; i<genre.artistsName.size() ; i++) {
                Artist artist = Artist.getArtistByName(genre.artistsName.get(i));
                FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowArtistHome-view.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowArtistsHomeController showArtistsHomeController = loader.getController();
                showArtistsHomeController.createArtistBox(artist);
                ArtistContainer.getChildren().add(vBox);
            }
        } catch (IOException e) {e.printStackTrace();}
        try {
            for (int i=0 ; i<genre.songs.size() ; i++) {
                Song song = genre.songs.get(i);
                FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowSongHome-view.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowSongsHomeController showSongsHomeController = loader.getController();
                showSongsHomeController.createSongBox(song);
                SongContainer.getChildren().add(vBox);
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
