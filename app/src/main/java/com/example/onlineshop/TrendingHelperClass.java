package com.example.onlineshop;

public class TrendingHelperClass {

    String id,image, title,description,trendingPrice;

    public TrendingHelperClass() {
    }

    public TrendingHelperClass(String id,String image, String title, String description,String trendingPrice) {
        this.id=id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.trendingPrice=trendingPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrendingPrice() {
        return trendingPrice;
    }

    public void setTrendingPrice(String trendingPrice) {
        this.trendingPrice = trendingPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
