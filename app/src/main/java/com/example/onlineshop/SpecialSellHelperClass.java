package com.example.onlineshop;

public class SpecialSellHelperClass {

    String id,image, discountNote,title,originalPrice,discountedPrice;

    public SpecialSellHelperClass() {
    }

    public SpecialSellHelperClass(String id,String image, String discountNote, String title, String originalPrice, String discountedPrice) {
        this.id=id;
        this.image = image;
        this.discountNote = discountNote;
        this.title = title;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
