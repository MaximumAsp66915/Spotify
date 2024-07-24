package com.example.spotify.Controller;

import com.example.spotify.Model.*;
import com.example.spotify.SpotifyRegisterMenu;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.spotify.Model.Genre.genreList;

public class SongPageController implements Initializable {

    @FXML
    private Label SongDiscription;

    @FXML
    private Label SongGenre;

    @FXML
    private Label SongArtist;

    @FXML
    private ImageView SongImage;

    @FXML
    private Label SongLabel;

    @FXML
    private Label SongYear;

    @FXML
    private VBox ArtistContainer;

    @FXML
    private VBox GenreContainer;

    @FXML
    private Button Play;

    @FXML
    private Button Stop;

    @FXML
    private Button Favorite;

    @FXML
    private ProgressBar progressBar;

    public static Song song ;
    private static MediaPlayer currentMediaPlayer = null;
    private MediaPlayer lastMediaPlayer = null;
    private static MediaPlayer mediaPlayer ;
    private static List<MediaPlayer> mediaPlayerList = new ArrayList<>();
    private boolean songHasBeenPlayed = false ;

    private DoubleProperty progress = new SimpleDoubleProperty();


    @FXML
    void back(ActionEvent event) {
        UpdateMenuController.back();
    }

    @FXML
    void play(ActionEvent event) {
        song.songIsReady = true ;
        currentMediaPlayer = mediaPlayer ;
        songHasBeenPlayed = true ;
        for(int i=0 ; i<mediaPlayerList.size();i++){
            if(!mediaPlayerList.get(i).equals(mediaPlayer)){
                mediaPlayerList.get(i).stop();
                Song.getSongByMedia(mediaPlayerList.get(i).getMedia()).songIsReady = false ;
                mediaPlayerList.remove(mediaPlayerList.get(i));
            }
        }
        if(!song.songIsPlaying) {
            mediaPlayer.play();
            song.songIsPlaying = true;
            Play.setText("Pause");
        } else {
            mediaPlayer.pause();
            song.songIsPlaying = false ;
            Play.setText("Play");
        }
    }

    @FXML
    void stop(ActionEvent event) {
        song.songIsReady= false ;
        mediaPlayer.stop();
        currentMediaPlayer = null;
    }

    @FXML
    void addToFavorite(ActionEvent event){
        if(song.songIsFavorite){
            User.onlineUser.playList.remove(song);
            song.songIsFavorite = false ;
            Favorite.setText("add to favorite");
        } else {
            User.onlineUser.playList.add(song);
            song.songIsFavorite = true ;
            Favorite.setText("remove from favorite");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(User.getSongInPlayListByName(song.name)!=null){
            song.songIsFavorite = true;
        }
        if(song.songIsFavorite){
            Favorite.setText("remove from favorite");
        } else {
            Favorite.setText("add to favorite");
        }
        mediaPlayer = new MediaPlayer(song.media);
        if(currentMediaPlayer!=null && currentMediaPlayer.getMedia().equals(song.media)){
            mediaPlayer = currentMediaPlayer ;
        }
        if(song.songIsReady && song.songIsPlaying){
            Play.setText("Pause");
        } else {
            Play.setText("Play");
        }
        mediaPlayerList.add(mediaPlayer);
        progress.bind(Bindings.createDoubleBinding(() ->
                        mediaPlayer.getCurrentTime().toMillis() / song.media.getDuration().toMillis(),
                mediaPlayer.currentTimeProperty()));
        progressBar.progressProperty().bind(progress);
        SongLabel.setText(song.name);
        SongImage.setImage(song.image);
        SongGenre.setText("Genre : " + song.genre.name);
        SongArtist.setText("Artist : " + song.artist.name);
        SongYear.setText("Year : " + song.date);
        SongDiscription.setText("Discription : " + song.discription);

        try {
            Artist artist = song.artist;
            FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowArtistHome-view.fxml"));
            Scene scene = new Scene(loader.load());
            VBox vBox = new VBox(scene.getRoot());
            ShowArtistsHomeController showArtistsHomeController = loader.getController();
            showArtistsHomeController.createArtistBox(artist);
            ArtistContainer.getChildren().add(vBox);
        } catch (IOException e) {e.printStackTrace();}
        try {
            Genre genre = song.genre;
            FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowGenreHome-view.fxml"));
            Scene scene = new Scene(loader.load());
            VBox vBox = new VBox(scene.getRoot());
            ShowGenresHomeController showGenresHomeController = loader.getController();
            showGenresHomeController.createGenreBox(genre);
            GenreContainer.getChildren().add(vBox);
        } catch (IOException e) {e.printStackTrace();}
    }
}
