package com.example.deni.masjidal_jihad.model;

import java.io.Serializable;

public class User implements Serializable {

    private String Username;
    private String Email;
    private String Password;
    private String NoTelp;
    private String Alamat;
    private String Level;
    private String Key;

    public User() {

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNoTelp() {
        return NoTelp;
    }

    public void setNoTelp(String noTelp) {
        NoTelp = noTelp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public User(String username, String email, String password, String noTelp, String alamat, String level) {
        Username = username;
        Email = email;
        Password = password;
        NoTelp = noTelp;
        Alamat = alamat;
        Level = level;
    }
}
