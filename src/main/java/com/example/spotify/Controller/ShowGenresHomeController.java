package com.example.spotify.Controller;

import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ShowGenresHomeController {
    @FXML
    private Label GenreLabel;
    @FXML
    private ImageView GenreImage;

    public void createGenreBox (Genre genre) {
        Image img = new Image(genre.image);
        GenreImage.setImage(img);
        GenreLabel.setText(genre.name);
    }
    @FXML
    void openGenre(MouseEvent event) {
        GenrePageController.genre = Genre.getGenreByName(GenreLabel.getText());
        UpdateMenuController.toGenrePage();
    }
}
