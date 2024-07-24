package com.example.spotify.Controller;

import com.example.spotify.Model.Artist;
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
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistPageController implements Initializable {

    @FXML
    private Label ArtistDescription;

    @FXML
    private VBox SongContainer;

    @FXML
    private Label ArtistGenre;

    @FXML
    private Label Nationality;

    @FXML
    private Label MonthlyListeners;

    @FXML
    private ImageView ArtistImage;

    @FXML
    private Label ArtistLabel;

    public static Artist artist ;

    @FXML
    void back(ActionEvent event) {
        UpdateMenuController.back();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArtistLabel.setText(artist.name);
        ArtistImage.setImage(new Image(artist.image));
        ArtistDescription.setText("Description : " + artist.description);
        MonthlyListeners.setText("Monthly listeners : " + artist.monthlyListener + " M");
        Nationality.setText("Nationality : " + artist.nationality);
        ArtistGenre.setText("Genres : " + artist.getGenres());

        try {
            for (int i=0 ; i<artist.songs.size() ; i++) {
                Song song = artist.songs.get(i);
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
