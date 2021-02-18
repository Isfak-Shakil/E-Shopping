package com.example.onlineshop;

public class ProductModel {
                 String p_image;
                String p_name;
                String p_rating;
                String p_price;

    public ProductModel() {
    }

    public ProductModel(String p_image, String p_name, String p_rating, String p_price) {
        this.p_image = p_image;
        this.p_name = p_name;
        this.p_rating = p_rating;
        this.p_price = p_price;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_rating() {
        return p_rating;
    }

    public void setP_rating(String p_rating) {
        this.p_rating = p_rating;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }
}
