package com.example.onlineshop;

public class CatLayoutHelperClass {
    String title;
    int itemCount;

    public CatLayoutHelperClass() {
    }

    public CatLayoutHelperClass(String title, int itemCount) {
        this.title = title;
        this.itemCount = itemCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
