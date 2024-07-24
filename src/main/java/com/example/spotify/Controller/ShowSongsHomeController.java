package com.example.spotify.Controller;

import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Song;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ShowSongsHomeController {
    @FXML
    private Label SongLabel;
    @FXML
    private ImageView SongImage;

    public void createSongBox (Song song) {
        Image img = song.image;
        SongImage.setImage(img);
        SongLabel.setText(song.name);
    }

    @FXML
    void openSong(MouseEvent event) {
        SongPageController.song = Song.getSongByName(SongLabel.getText());
        UpdateMenuController.toSongPage();
    }

}
