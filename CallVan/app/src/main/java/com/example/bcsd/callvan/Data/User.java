package com.example.bcsd.callvan.Data;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Infromation of User
 */
public class User {
    private String email;
    private String password;
    private String device;
    private String location;
    private boolean login_status;



    public User(String email, String password, String device) {
        this.email = email;
        this.password = password;
        this.device = device;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isLogin_status() {
        return login_status;
    }

    public void setLogin_status(boolean login_status) {
        this.login_status = login_status;
    }
}
