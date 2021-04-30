package com.example.deni.masjidal_jihad.model;

public class AgendaKhutbah {

    private String Key;
    private String Tanggal;
    private String Khatib;
    private String Pekan;

    public AgendaKhutbah(){

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

    public String getKhatib() {
        return Khatib;
    }

    public void setKhatib(String khatib) {
        Khatib = khatib;
    }

    public String getPekan() {
        return Pekan;
    }

    public void setPekan(String pekan) {
        Pekan = pekan;
    }

    public AgendaKhutbah(String tanggal, String khatib, String pekan) {
        Tanggal = tanggal;
        Khatib = khatib;
        Pekan = pekan;
    }


}
