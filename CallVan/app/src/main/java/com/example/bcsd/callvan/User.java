package com.example.bcsd.callvan;

/**
 * Created by EUNBEE on 2016-05-26.
 */
public class User {
    private String email;
    private String password;
    private String device;
    private String location;
    private boolean login_status;

    //Get
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getDevice(){
        return device;
    }
    public String getLocation(){
        return location;
    }
    public boolean isLogin_status(){
        return login_status;
    }

    //Set
    public void setLogin_status(boolean status){
        login_status = status;
    }
    public void setLocation(String location){
        this.location = location;
    }

}
