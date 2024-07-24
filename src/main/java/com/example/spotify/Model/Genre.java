package com.example.spotify.Model;

import java.util.ArrayList;
import java.util.List;

public class Genre {
    public static List<Genre> genreList = new ArrayList<>();
    public List<Song> songs = new ArrayList<>();
    public List<String> artistsName = new ArrayList<>();
    public String name ;
    public String image ;
    public Genre(String name , String image , String artist){
        this.name = name ;
        this.image = image ;
    }
    public static Genre addGenre(String name , String image , String artist){
        if(getGenreByName(name)==null){
            genreList.add(new Genre(name, image, artist));
        }
        if(getArtistByNameInGenre(name , image , artist)==null && !artist.equals("")){
            getGenreByName(name).artistsName.add(artist);
        }
        return getGenreByName(name);
    }
    public static Genre getGenreByName(String name){
        for (Genre genre : genreList) {
            if (genre.name.equals(name)) {
                return genre;
            }
        }
        return null;
    }
    public static String getArtistByNameInGenre(String name , String image , String artistName){
        for(int i=0 ; i<getGenreByName(name).artistsName.size() ; i++){
            if(getGenreByName(name).artistsName.get(i).equals(artistName)){
                return getGenreByName(name).artistsName.get(i);
            }
        }
        return null;
    }
    public static void addGenreImage(String name , String image){
        if(getGenreByName(name)==null){
            addGenre(name, image, "");
        }
        getGenreByName(name).image = image ;
    }
}
