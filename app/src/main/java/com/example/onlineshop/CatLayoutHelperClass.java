package com.example.onlineshop;

public class CatLayoutHelperClass {
    String title;
   String itemCount;

    public CatLayoutHelperClass() {
    }

    public CatLayoutHelperClass(String title, String itemCount) {
        this.title = title;
        this.itemCount = itemCount;
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
