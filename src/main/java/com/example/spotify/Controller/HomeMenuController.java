package com.example.spotify.Controller;

import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Genre;
import com.example.spotify.Model.Song;
import com.example.spotify.SpotifyRegisterMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.spotify.Model.Artist.artistList;
import static com.example.spotify.Model.Genre.addGenreImage;
import static com.example.spotify.Model.Genre.genreList;

public class HomeMenuController implements Initializable {

    @FXML
    private HBox ArtistContainer ;

    @FXML
    private HBox GenreContainer ;

    @FXML
    private ScrollPane ScrollerArtist;

    @FXML
    private TextField SearchBox;

    @FXML
    private Button Profile;

    @FXML
    private Label Result;

    private static List<String> data = new ArrayList<>();
    private static String result ;

    @FXML
    void profile(ActionEvent event) {
        UpdateMenuController.toProfileMenu();
    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }

    private static boolean oneTimeInitialize = true ;

    @FXML
    void getTheResult(MouseEvent event) {
        if(result!=null) {
            if(Artist.getArtistByName(result)!=null){
                ArtistPageController.artist = Artist.getArtistByName(result);
                UpdateMenuController.toArtistPage();
            }else if(Genre.getGenreByName(result)!=null){
                GenrePageController.genre = Genre.getGenreByName(result);
                UpdateMenuController.toGenrePage();
            }else if(Song.getSongByName(result)!=null){
                SongPageController.song = Song.getSongByName(result);
                UpdateMenuController.toSongPage();
            }
        }
    }

    private String search(String query) {
        Optional<String> closestMatch = data.stream()
                .min((s1, s2) -> Integer.compare(levenshteinDistance(s1.toLowerCase(), query.toLowerCase()),
                        levenshteinDistance(s2.toLowerCase(), query.toLowerCase())));

        return closestMatch.orElse(null);
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    private int costOfSubstitution(char a, char b) {return a == b ? 0 : 1;}

    private int min(int... numbers) {return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Result.setText("...");
        if(oneTimeInitialize){
            oneTimeInitialize = false;
            oneTimeInitialize();
        }
        SearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            result = search(newValue);
            Result.setText(result != null ? result + " ?" : "No match found");
            if(newValue==null || newValue.equals("")){
                SearchBox.setPromptText("Search");
            }
        });
        try {
            for (Artist artist : artistList) {
                FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowArtistHome-view.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowArtistsHomeController showArtistsHomeController = loader.getController();
                showArtistsHomeController.createArtistBox(artist);
                ArtistContainer.getChildren().add(vBox);
            }
        } catch (IOException e) {e.printStackTrace();}
        try {
            for (Genre genre : genreList) {
                FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowGenreHome-view.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowGenresHomeController showGenresHomeController = loader.getController();
                showGenresHomeController.createGenreBox(genre);
                GenreContainer.getChildren().add(vBox);
            }
        } catch (IOException e) {e.printStackTrace();}
    }
    private void oneTimeInitialize(){
        // template :         artistList.add(new Artist("" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "") , Genre.addGenre("Rock", "", "")} , 0 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/.png" ,""));
        artistList.add(new Artist("The Weeknd" , "American" , new Genre[]{Genre.addGenre("Pop", "", "The Weeknd")}, 106.04 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/The Weeknd.png" ,"Abel Makkonen Tesfaye (born February 16, 1990), known professionally as the Weeknd, is a Canadian\n singer-songwriter. He is known for his unconventional music production, artistic reinventions, and signature use of\n the falsetto register."));
        artistList.add(new Artist("Taylor Swift" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Taylor Swift") , Genre.addGenre("Rock", "", "Taylor Swift")} , 99.60 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Taylor Swift.png" ,"Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. A subject of\n widespread public interest with a vast fanbase, she has influenced the music industry, popular culture, and politics\n through her songwriting, artistry, and advocacy."));
        artistList.add(new Artist("Billie Eilish" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Billie Eilish") , Genre.addGenre("Rock", "", "Billie Eilish")} , 98.81 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Billie Eilish.png" ,"Billie Eilish Pirate Baird O'Connell (born December 18, 2001) is an American singer and songwriter. She first gained public attention in 2015 with her debut single \"Ocean Eyes\", written and produced by her brother Finneas O'Connell, with whom she collaborates on music and live shows. In 2017, she released her debut extended play , Don't Smile at Me. Commercially successful, it reached the top 15 of record charts in numerous countries, including the US, UK, Canada, and Australia."));
        artistList.add(new Artist("Post Malone" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Post Malone") , Genre.addGenre("Hip hop", "", "Post Malone") , Genre.addGenre("Pop rap", "", "Post Malone") , Genre.addGenre("Pop rock", "", "Post Malone")} , 88.08 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Post Malone.png" ,"Austin Richard Post (born July 4, 1995), known professionally as Post Malone, is an American rapper, singer, songwriter, and record producer. Malone has gained distinction and acclaim for his blending of various genres including hip hop, pop, R&B, and trap. His stage name was derived from inputting his birth name into a rap name generator."));
        artistList.add(new Artist("Eminem" , "American" ,new Genre[]{Genre.addGenre("Hip hop", "", "Eminem")} , 84.98 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Eminem.png" ,"Marshall Bruce Mathers III (born October 17, 1972), known professionally as Eminem (also stylized as EMINƎM), is an American rapper. He is credited with popularizing hip hop in Middle America and is regarded as one of the greatest rappers of all time."));
        artistList.add(new Artist("Rihanna" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Rihanna") , Genre.addGenre("Hip hop", "", "Rihanna")} , 84.80 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Rihanna.png" ,"Robyn Rihanna Fenty (born February 20, 1988) is a Barbadian singer, businesswoman and actress. She was cited as the best-selling female recording artist of the 21st century by Guinness World Records and is one of the best-selling recording artists of all time, with sales estimated at 250 million units worldwide. Rihanna is the highest-certified female digital single artist and the only female artist to have seven U.S. diamond certified singles. She has achieved 14 number-one singles on the Billboard Hot 100. The recipient of numerous awards and nominations, Rihanna remains the wealthiest female musical artist with an estimated net worth of $1.4 billion as of 2024."));
        artistList.add(new Artist("Sabrina Carpenter" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Sabrina Carpenter")} , 81.93 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Sabrina Carpenter.png" ,"Sabrina Annlynn Carpenter (born May 11, 1999) is an American singer and actress. She first gained recognition for starring in the Disney Channel series Girl Meets World (2014–2017). She signed to Disney-owned Hollywood Records in 2013 and released her debut single, \"Can't Blame a Girl for Trying\", the following year. In the 2010s, she released four studio albums—Eyes Wide Open (2015), Evolution (2016), Singular: Act I (2018), and Singular: Act II (2019)—to limited commercial success; the singles \"Alien\", \"Almost Love\", and \"Sue Me\" topped the United States Dance Club Songs chart."));
        artistList.add(new Artist("Coldplay" , "British" ,new Genre[]{Genre.addGenre("Pop", "", "Coldplay") , Genre.addGenre("Pop rock", "", "Coldplay") , Genre.addGenre("Britpop", "", "Coldplay")} , 81.00 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Coldplay.png" ,"Coldplay are a British rock band formed in London in 1997, consisting of vocalist and pianist Chris Martin, lead guitarist Jonny Buckland, bassist Guy Berryman, drummer and percussionist Will Champion, and manager Phil Harvey. They are best known for their live performances, having also impacted popular culture with their artistry, advocacy and achievements."));
        artistList.add(new Artist("Ariana Grande" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Ariana Grande")} , 80.23 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Ariana Grande.png" ,"Ariana Grande-Butera (June 26, 1993) is an American singer, songwriter and actress. Regarded as a pop icon and an influential figure in popular music, she is noted for her four-octave vocal range and her signature use of the whistle register. Grande's various awards include two Grammy Awards, one Brit Award, two Billboard Music Awards, three American Music Awards, nine MTV Video Music Awards, and 36 Guinness World Records. Rolling Stone named her one of the greatest vocalists of all time."));
        artistList.add(new Artist("David Guetta" , "French" ,new Genre[]{Genre.addGenre("Dance-pop", "", "David Guetta")} , 76.70 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/David Guetta.png" ,"Pierre David Guetta (born 7 November 1967) is a French DJ and record producer. He has sold over 10 million albums and 65 million singles globally, with more than 14 billion streams. Guetta was voted the number one DJ in the DJ Mag Top 100 DJs polls in 2011, and throughout 2020 until 2023. In 2013, Billboard ranked his song \"When Love Takes Over\" (featuring Kelly Rowland) as the number one dance-pop collaboration ever."));
        artistList.add(new Artist("Drake" , "Canadian" ,new Genre[]{Genre.addGenre("Hip hop", "", "Drake") , Genre.addGenre("Pop rap", "", "Drake") , Genre.addGenre("Pop trap", "", "Drake")} , 76.55 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Drake.png" ,"Aubrey Drake Graham (born October 24, 1986) is a Canadian rapper, singer and actor. An influential figure in popular music, he has been credited with popularizing R&B sensibilities in hip hop artists. Gaining recognition by starring as Jimmy Brooks in the CTV teen drama series Degrassi: The Next Generation (2001–2008), Drake began his recording career in 2006 with the release of his debut mixtape, Room for Improvement (2006). He followed up with the mixtapes Comeback Season (2007) and So Far Gone (2009) before signing with Young Money Entertainment."));
        artistList.add(new Artist("Dua Lipa" , "British" ,new Genre[]{Genre.addGenre("Pop", "", "Dua Lipa") , Genre.addGenre("Dance-pop", "", "Dua Lipa")} , 74.64 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Dua Lipa.png" ,"Dua Lipa (born 22 August 1995) is an English and Albanian singer and songwriter. Her accolades include seven Brit Awards and three Grammy Awards. Time included her in its list of the top 100 most influential people in the world for 2024."));
        artistList.add(new Artist("Justin Bieber" , "Canadian" ,new Genre[]{Genre.addGenre("Pop", "", "Justin Bieber") , Genre.addGenre("Dance-pop", "", "Justin Bieber")} , 74.25 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Justin Bieber.png" ,"Justin Drew Bieber (born March 1, 1994) is a Canadian singer. Regarded as a pop icon, he is recognized for his multi-genre musical performances. He was discovered by American record executive Scooter Braun in 2008 and subsequently brought to American singer Usher, both of whom formed the record label RBMG Records to sign Bieber in October of that year. He gained recognition following the release of his debut extended play My World (2009), which was quickly met with international commercial success and led to his establishment as a teen idol."));
        artistList.add(new Artist("Bruno Mars" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "Bruno Mars")} , 73.30 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Bruno Mars.png" ,"Peter Gene Hernandez (born October 8, 1985), known professionally as Bruno Mars, is an American singer-songwriter. He is known for his stage performances, retro showmanship, and for performing in a wide range of musical styles, including pop, R&B, funk, soul, reggae, disco, and rock. Mars is accompanied by his band, the Hooligans, who play a variety of instruments, such as electric guitar, bass, piano, keyboards, drums, and horns, and also serve as backup singers and disco dancers. In 2021, he collaborated with Anderson .Paak, as the American musical superduo Silk Sonic."));
        artistList.add(new Artist("Kendrick Lamar" , "American" ,new Genre[]{Genre.addGenre("Jazz rap", "", "Kendrick Lamar") , Genre.addGenre("Hip hop", "", "Kendrick Lamar")} , 72.90 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Kendrick Lamar.png" ,"Kendrick Lamar Duckworth (born June 17, 1987) is an American rapper and songwriter. Often regarded as one of the greatest rappers of all time, he is the only musician outside of the classical and jazz genres to be awarded the Pulitzer Prize for Music. His songwriting, noted for its cinematic approach, often includes social commentary and political criticism."));
        artistList.add(new Artist("SZA" , "American" ,new Genre[]{Genre.addGenre("Pop", "", "SZA") , Genre.addGenre("Hip hop", "", "SZA")} , 71.70 , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/SZA.png" ,"Solána Imani Rowe (born November 8, 1989), known professionally as SZA , is an American singer-songwriter. She first gained recognition through her self-released EPs See.SZA.Run (2012) and S (2013), which helped her become the first female artist to sign with Top Dawg Entertainment. Her third EP, (2014), was her first project to be released to digital retailers and reached the top-ten on the US Independent Albums chart."));


        for (Genre genre : genreList) {
            genre.image = "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Pop.png";
        }
        addGenreImage("Pop" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Pop.png");
        addGenreImage("Hip hop" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Hip hop.png");
        addGenreImage("Rock" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Rock.png");
        addGenreImage("Pop rap" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Pop rap.png");
        addGenreImage("Pop rock" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Pop rock.png");
        addGenreImage("Britpop" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Britpop.png");
        addGenreImage("Dance-pop" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Dance-pop.png");
        addGenreImage("Pop trap" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Pop trap.png");
        addGenreImage("Jazz rap" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Jazz rap.png");

        Song.addSong("Save your tears" , "The Weeknd" , "Pop" , "file:/C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/media/Saveyourtears.mp3" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Save your tears.png" , "2020" , "\"Save Your Tears\" is a song by the Canadian \nsinger-songwriter the Weeknd from his fourth studio album,\n After Hours (2020). It was released to Dutch contemporary \nhit radio on August 9, 2020 as the album's fourth and final\n single. The song was written and produced by the Weeknd\n, Max Martin, and Oscar Holter, with Belly and DaHeala \nreceiving additional writing credits.");
        Song.addSong("Fortnight By TaylorSwift" , "Taylor Swift" , "Pop" , "file:/C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/media/Fortnight.mp3" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Fortnight Taylor Swift.png" , "2024" , "\"Fortnight\" is a song by the American singer-songwriter Taylor Swift featuring the American rapper and singer Post Malone, taken from Swift's eleventh studio album, The Tortured Poets Department. The two artists wrote the track with Jack Antonoff, who produced it with Swift. Republic Records released the song as the lead single concurrently with its parent album on April 19, 2024. A 1980s-inspired downtempo electropop and synth-pop ballad, \"Fortnight\" is instrumented by a pulsing synth bassline. Its lyrics portray a woman in an unhappy marriage who becomes next-door neighbors with an ex-lover who is also married, and the two vow to escape to Florida.");
        Song.addSong("Fortnight By Post Malone" , "Post Malone" , "Pop" , "file:/C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/media/Fortnight.mp3" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Fortnight Post Malone.png" , "2024" , "\"Fortnight\" is a song by the American singer-songwriter Taylor Swift featuring the American rapper and singer Post Malone, taken from Swift's eleventh studio album, The Tortured Poets Department. The two artists wrote the track with Jack Antonoff, who produced it with Swift. Republic Records released the song as the lead single concurrently with its parent album on April 19, 2024. A 1980s-inspired downtempo electropop and synth-pop ballad, \"Fortnight\" is instrumented by a pulsing synth bassline. Its lyrics portray a woman in an unhappy marriage who becomes next-door neighbors with an ex-lover who is also married, and the two vow to escape to Florida.");
        Song.addSong("All too well" , "Taylor Swift" , "Pop" , "file:/C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/media/Alltoowell.mp3" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/All too well.png" , "2012" , "\"All Too Well\" is a song by the American singer-songwriter Taylor Swift. The song was developed by Swift on the Speak Now World Tour in 2011, and she co-wrote the final version with Liz Rose. The song was first produced by Swift and Nathan Chapman for her fourth studio album, Red (2012). After a 2019 dispute regarding the ownership of Swift's masters, she re-recorded the song as \"Taylor's Version\" and released an unabridged \"10 Minute Version\" as part of the re-recorded album Red (Taylor's Version) in November 2021.");
        Song.addSong("Lovely" , "Billie Eilish" , "Pop" , "file:/C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/media/Lovely.mp3" , "File:C:/Users/Asus/IdeaProjects/Spotify/src/main/resources/com/example/spotify/image/Lovely.png" , "2018" , "\"Lovely\" is a song by American singers Billie Eilish and Khalid. Darkroom and Interscope Records released it as the lead single from the Netflix drama series 13 Reasons Why's second season soundtrack. The artists wrote the song with Eilish's brother and producer Finneas O'Connell. The song has been described as a chamber pop ballad whose lyrics recount Eilish and Khalid trying to overcome serious depression together. The song also appears on the deluxe version of Eilish's EP don't smile at me.");

        for (Artist artist : artistList) {
            data.add(artist.name);
        }
        for (Genre genre : genreList) {
            data.add(genre.name);
        }
        for (Song song : Song.songList) {
            data.add(song.name);
        }
    }
}
