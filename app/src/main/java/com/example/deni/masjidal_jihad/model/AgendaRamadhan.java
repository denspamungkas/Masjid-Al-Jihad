package com.example.deni.masjidal_jihad.model;

public class AgendaRamadhan {

   private String Key;
   private String Tanggal;
   private String Nama;
   private String Alamat;

   public AgendaRamadhan(){

    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public AgendaRamadhan(String tanggal, String nama, String alamat) {
        Tanggal = tanggal;
        Nama = nama;
        Alamat = alamat;
    }
}
