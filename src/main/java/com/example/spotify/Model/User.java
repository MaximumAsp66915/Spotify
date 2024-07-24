package com.example.spotify.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static List<User> userList = new ArrayList<>();
    public List<Song> playList = new ArrayList<>();
    public static User onlineUser ;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String dateOfBirth = "";
    private String monthOfBirth = "";
    private String yearOfBirth = "";
    private String gender = "";
    private User(String username , String password , String nickname , String email){
        this.username = username ;
        this.password = password ;
        this.nickname = nickname ;
        this.email = email ;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public static Result addList(String username , String password , String nickname , String email){
        if(username.equals("") || password.equals("") || nickname.equals("") || email.equals("")){
            return new Result(false , "Complete all the fields!");
        }
        if (!username.matches("[\\w\\d_]+")){
            return new Result(false, "Username's pattern is incorrect!");
        }
        if(getUserByUsername(username)!=null){
            return new Result(false , "This user already exits!");
        }
        if ((password.length()<8 || password.length()>30)){
            return new Result(false, "Password is too short or too long!");
        }
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[]!@#$%^&*()_+\\-=\\[\\]{}?<>/?~].*")){
            return new Result(false, "Password is weak!");
        }
        if(!email.matches("^(?<email>[\\S]+)@(?<domain>[\\S]+)\\.com$")){
            return new Result(false, "Email is invalid!");
        }

        userList.add(new User(username , password , nickname , email));
        return new Result(true , "Successful");
    }
    public static Result signInUser(String username , String password){
        if(username.equals("") || password.equals("")){
            return new Result(false , "Complete all the fields!");
        }
        if(getUserByUsername(username)==null){
            return new Result(false , "This user doesn't exit!");
        }
        if(!getUserByUsername(username).password.equals(password)){
            return new Result(false , "Wrong password!");
        }

        onlineUser = getUserByUsername(username);
        return new Result(true , "Signed in");
    }
    public Result updateProfile(String username , String email , String gender , String date , String month , String year){
        if(!preventNullText(email).matches("^(?<email>[\\S]+)@(?<domain>[\\S]+)\\.com$")){
            return new Result(false, "Email is invalid!");
        }
        if(!preventNullText(email).equals(""))
            this.email = preventNullText(email) ;
        if(!preventNullText(gender).equals(""))
            this.gender = preventNullText(gender) ;
        if(!preventNullText(date).equals(""))
            this.dateOfBirth = preventNullText(date) ;
        if(!preventNullText(month).equals(""))
            this.monthOfBirth = preventNullText(month) ;
        if(!preventNullText(year).equals(""))
            this.yearOfBirth = preventNullText(year) ;
        return new Result(true , "Profile updated");
    }
    private static User getUserByUsername(String username){
        for (User user : userList) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
    private static String preventNullText(Object object){
        if(object==null){
            return "";
        }
        return (String) object;
    }
    public static Song getSongInPlayListByName(String songName){
        for(int i=0 ; i<onlineUser.playList.size() ; i++){
            if(onlineUser.playList.get(i).name.equals(songName)){
                return onlineUser.playList.get(i);
            }
        }
        return null;
    }
}
