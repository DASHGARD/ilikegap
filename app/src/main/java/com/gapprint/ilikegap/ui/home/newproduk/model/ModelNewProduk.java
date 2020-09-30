package com.gapprint.ilikegap.ui.home.newproduk.model;

public class ModelNewProduk {

    private String id;
    private String nama;
    private String gambar;
    private String link;



    public ModelNewProduk(String id,String nama, String gambar, String link) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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