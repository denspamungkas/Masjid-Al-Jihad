package com.example.deni.masjidal_jihad.model;

public class AgendaMaulid {

    private String Key;
    private String Judul;
    private String Tanggal;
    private String Isi;

    public AgendaMaulid() {
    }


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public AgendaMaulid(String judul, String tanggal, String isi) {
        Judul = judul;
        Tanggal = tanggal;
        Isi = isi;
    }
}
