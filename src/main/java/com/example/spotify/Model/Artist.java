package com.example.spotify.Model;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    public static List<Artist> artistList = new ArrayList<>();
    public List<Song> songs = new ArrayList<>();
    public String name;
    public String nationality;
    public List<Genre> genres = new ArrayList<>();
    public double monthlyListener;
    public String image;
    public String description;
    public Artist(String name,String nationality ,Genre[] genres , double monthlyListener , String  image , String description){
        this.name = name;
        this.nationality = nationality;
        this.monthlyListener = monthlyListener;
        this.image = image;
        this.description = description;
        for(int i=0 ; i<genres.length ; i++){
            this.genres.add(genres[i]);
        }
    }
    public static Artist getArtistByName(String name){
        for (Artist artist : artistList) {
            if (artist.name.equals(name)) {
                return artist;
            }
        }
        return null;
    }

    public String getGenres(){
        String output = "" ;
        for(int i=0 ; i<this.genres.size() ; i++){
            output+=(genres.get(i).name);
            if((i+1)!=this.genres.size()){
                output+=" , ";
            }
        }
        return output;
    }

}
