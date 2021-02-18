package com.example.onlineshop;

public class CatLayoutHelperClass {
    String catId;
    String title;
   String itemCount;
   String imageUrl;

    public CatLayoutHelperClass() {
    }

    public CatLayoutHelperClass(String title, String itemCount,String imageUrl,String catId) {
        this.catId=catId;
        this.title = title;
        this.itemCount = itemCount;
        this.imageUrl=imageUrl;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
}
