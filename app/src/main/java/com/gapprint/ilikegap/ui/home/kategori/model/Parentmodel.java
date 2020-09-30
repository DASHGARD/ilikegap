package com.gapprint.ilikegap.ui.home.kategori.model;

import android.content.Intent;

public class Parentmodel {

    private String nama;
    private String gambar;
    private String link;



    public Parentmodel(String nama, String gambar, String link) {
        this.nama = nama;
        this.gambar = gambar;
        this.link = link;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}