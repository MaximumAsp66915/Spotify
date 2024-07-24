package com.example.spotify.Controller;

import com.example.spotify.Model.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ShowArtistsHomeController {
    @FXML
    private Label ArtistLabel;
    @FXML
    private ImageView ArtistImage;

    public void createArtistBox (Artist artist) {
        Image img = new Image(artist.image);
        ArtistImage.setImage(img);
        ArtistLabel.setText(artist.name);
    }

    @FXML
    void openArtist(MouseEvent event) {
        ArtistPageController.artist = Artist.getArtistByName(ArtistLabel.getText());
        UpdateMenuController.toArtistPage();
    }

}
