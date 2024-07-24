package com.example.spotify.Controller;

import com.example.spotify.Model.Result;
import com.example.spotify.Model.Song;
import com.example.spotify.Model.User;
import com.example.spotify.SpotifyRegisterMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenuController implements Initializable {

    @FXML
    private ComboBox<String> Date;

    @FXML
    private TextField Email;

    @FXML
    private Label ErrorText;

    @FXML
    private ComboBox<String> Month;

    @FXML
    private Button SaveProfile;

    @FXML
    private TextField Username;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private ComboBox<String> Gender;

    @FXML
    private VBox SongContainer;

    @FXML
    void logout(ActionEvent event) {
        UpdateMenuController.toSignInMenu();
    }

    @FXML
    void saveProfile(ActionEvent event) {
        Result result = User.onlineUser.updateProfile(Username.getText() , Email.getText() , Gender.getValue() , Date.getValue() , Month.getValue() , Year.getValue());
        ErrorText.setText(result.getMessage());
        if(result.isSuccessful()){
            UpdateMenuController.toHomeMenu();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        UpdateMenuController.toHomeMenu();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            for (int i=0 ; i<User.onlineUser.playList.size() ; i++) {
                Song song = User.onlineUser.playList.get(i);
                FXMLLoader loader = new FXMLLoader(SpotifyRegisterMenu.class.getResource("ShowSongHome-view.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowSongsHomeController showSongsHomeController = loader.getController();
                showSongsHomeController.createSongBox(song);
                SongContainer.getChildren().add(vBox);
            }
        } catch (IOException e) {e.printStackTrace();}

        ErrorText.setText("");
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male",
                "Female",
                "Rather not to say"
        );
        Gender.setItems(genderOptions);

        ObservableList<String> monthOptions = FXCollections.observableArrayList(
                "January" ,
                "February" ,
                "March" ,
                "April" ,
                "May" ,
                "June" ,
                "July" ,
                "August" ,
                "September" ,
                "October" ,
                "November" ,
                "December"
        );
        Month.setItems(monthOptions);

        ObservableList<String> dateOptions = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23",
                "24",
                "25",
                "26",
                "27",
                "28",
                "29",
                "30"
        );
        Date.setItems(dateOptions);

        ObservableList<String> yearOptions = FXCollections.observableArrayList(
                "1900",
                "1901",
                "1902",
                "1903",
                "1904",
                "1905",
                "1906",
                "1907",
                "1908",
                "1909",
                "1910",
                "1911",
                "1912",
                "1913",
                "1914",
                "1915",
                "1916",
                "1917",
                "1918",
                "1919",
                "1920",
                "1921",
                "1922",
                "1923",
                "1924",
                "1925",
                "1926",
                "1927",
                "1928",
                "1929",
                "1930",
                "1931",
                "1932",
                "1933",
                "1934",
                "1935",
                "1936",
                "1937",
                "1938",
                "1939",
                "1940",
                "1941",
                "1942",
                "1943",
                "1944",
                "1945",
                "1946",
                "1947",
                "1948",
                "1949",
                "1950",
                "1951",
                "1952",
                "1953",
                "1954",
                "1955",
                "1956",
                "1957",
                "1958",
                "1959",
                "1960",
                "1961",
                "1962",
                "1963",
                "1964",
                "1965",
                "1966",
                "1967",
                "1968",
                "1969",
                "1970",
                "1971",
                "1972",
                "1973",
                "1974",
                "1975",
                "1976",
                "1977",
                "1978",
                "1979",
                "1980",
                "1981",
                "1982",
                "1983",
                "1984",
                "1985",
                "1986",
                "1987",
                "1988",
                "1989",
                "1990",
                "1991",
                "1992",
                "1993",
                "1994",
                "1995",
                "1996",
                "1997",
                "1998",
                "1999",
                "2000",
                "2001",
                "2002",
                "2003",
                "2004",
                "2005",
                "2006",
                "2007",
                "2008",
                "2009",
                "2010",
                "2011",
                "2012",
                "2013",
                "2014",
                "2015",
                "2016",
                "2017",
                "2018",
                "2019",
                "2020",
                "2021",
                "2022",
                "2023",
                "2024"
        );
        Year.setItems(yearOptions);

        Email.setText(User.onlineUser.getEmail());
        Username.setText(User.onlineUser.getUsername());
        Username.setDisable(true);
        if(!User.onlineUser.getDateOfBirth().equals("")){
            Date.setPromptText(User.onlineUser.getDateOfBirth());
        }
        if(!User.onlineUser.getMonthOfBirth().equals("")){
            Month.setPromptText(User.onlineUser.getMonthOfBirth());
        }
        if(!User.onlineUser.getYearOfBirth().equals("")){
            Year.setPromptText(User.onlineUser.getYearOfBirth());
        }
        if(!User.onlineUser.getGender().equals("")){
            Gender.setPromptText(User.onlineUser.getGender());
        }
    }
}


