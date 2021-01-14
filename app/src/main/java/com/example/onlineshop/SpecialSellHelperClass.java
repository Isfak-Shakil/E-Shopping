package com.example.onlineshop;

public class SpecialSellHelperClass {
    int image;
    String discountNote,title,originalPrice,discountedPrice;

    public SpecialSellHelperClass() {
    }

    public SpecialSellHelperClass(int image, String discountNote, String title, String originalPrice, String discountedPrice) {
        this.image = image;
        this.discountNote = discountNote;
        this.title = title;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDiscountNote() {
        return discountNote;
    }

    public void setDiscountNote(String discountNote) {
        this.discountNote = discountNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
