package com.example.spotify.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Song{
    public static List<Song> songList = new ArrayList<>();
    public String name ;
    public Artist artist ;
    public Genre genre ;
    public Media media ;
    public Image image;
    public String discription = "";
    public String date = "";
    public boolean songIsPlaying = false ;
    public boolean songIsReady = false ;
    public boolean songIsFavorite = false ;

    private Song (String name , String artistName , String genreName,String path , String imagePath , String date ,String discription){
        this.name = name ;
        this.artist = Artist.getArtistByName(artistName);
        this.genre = Genre.getGenreByName(genreName);
        this.date = date ;
        this.discription = discription ;
        if(path.equals("")){
            this.media = new Media("file:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/media/Saveyourtears.mp3");
        } else {
            this.media = new Media(path);
        }
        if(imagePath.equals("")){
            this.image = new Image("File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Save your tears.png");
        } else {
            this.image = new Image(imagePath);
        }
    }
    public static Song addSong(String name , String artistName , String genreName , String path , String imagePath , String date ,String discription){
        Song song = new Song(name , artistName ,genreName , path , imagePath , date , discription);
        songList.add(song);
        song.artist.songs.add(song);
        song.genre.songs.add(song);
        return song;
    }
    public static Song getSongByName(String name){
        for (Song song : songList) {
            if (song.name.equals(name)) {
                return song;
            }
        }
        return null;
    }
    public static Song getSongByMedia(Media media){
        for (Song song : songList) {
            if (song.media.equals(media)) {
                return song;
            }
        }
        return null;
    }
}
