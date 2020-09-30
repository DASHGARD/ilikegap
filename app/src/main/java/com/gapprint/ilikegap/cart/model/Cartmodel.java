package com.gapprint.ilikegap.cart.model;

public class Cartmodel {

    private String title;
    private String description;
    private String thumbnail;
    private String studio;
    private int price;
    private int totalharga;
    private String rating;
    private int qty;
    private String streamingLink;
    private Boolean selected = false;
    private int coverPhoto;



    public Cartmodel(String title, String thumbnail, int price,int totalharga,Boolean selected, int qty) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.price = price;
        this.totalharga = totalharga;
        this.selected = selected;
        this.qty = qty;

    }



    public Cartmodel(String title, String description, String thumbnail,Boolean selected,int price,int totalharga, String studio, String rating, String streamingLink) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.price = price;
        this.totalharga = totalharga;
        this.streamingLink = streamingLink;
        this.selected = selected;

    }


    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public int getQty() {
        return qty;
    }
    public int getPrice() {
        return price;
    }
    public int getTotalharga() {
        return totalharga;
    }

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public boolean getSelected(){
        return selected;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public void setPrice(int position,int price) {
        this.price = price;
    }
    public void setQty( int position, int qty) {
        this.qty = qty   ;
    }
    public void setTotalharga( int position, int totalharga) {
        this.totalharga = totalharga   ;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

    public void setSelected(int position, boolean selected) {
        this.selected = selected;
    }
}
