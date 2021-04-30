package com.example.deni.masjidal_jihad.model;

import java.io.Serializable;

public class Iuran implements Serializable {

    private String Nama;
    private String Bulan;
    private String Iuran;
    private String Image;
    private String Validasi;
    private String Username;
    private String Key;

    public Iuran() {

    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getBulan() {
        return Bulan;
    }

    public void setBulan(String bulan) {
        Bulan = bulan;
    }

    public String getIuran() {
        return Iuran;
    }

    public void setIuran(String iuran) {
        Iuran = iuran;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getValidasi() {
        return Validasi;
    }

    public void setValidasi(String validasi) {
        Validasi = validasi;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Iuran(String nama, String bulan, String iuran, String image, String validasi, String username) {
        Nama = nama;
        Bulan = bulan;
        Iuran = iuran;
        Image = image;
        Validasi = validasi;
        Username = username;
    }
}
