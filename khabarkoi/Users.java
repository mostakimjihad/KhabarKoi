package com.example.khabarkoi;

public class Users {

    String fullname, username, email, password, confirmpassword, imageURL;

    public Users(){

    }

    public Users(String fullname, String username, String email, String password, String confirmpassword,  String imageURL){

        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;

        this.imageURL = imageURL;

    }


    public String getFullname(){
        return fullname;
    }
    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getConfirmpassword(){
        return confirmpassword;
    }
    public void setConfirmpassword(String confirmpassword){
        this.confirmpassword = confirmpassword;
    }


    public String getImageURL(){return imageURL;}
    public void setImageURL(String imageURL){this.imageURL = imageURL;}

}
