package com.example.onlineshop;

public class ImageHelperClass {
    String catId;
  private String imageUrl;
  private String name;


    public ImageHelperClass() {
    }

    public ImageHelperClass(String imageUrl,String name,String catId) {
        this.imageUrl = imageUrl;
        this.name=name;
        this.catId=catId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
