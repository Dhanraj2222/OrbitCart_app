package com.orbit.models;

import com.google.gson.annotations.SerializedName;

public class Product {
    private int id;
    private String name;
    private double price;
    private int maxQty;
    private int quantity;
    @SerializedName("image_url")
    private String imageUrl;

    public Product(int id, String name, double price, int maxQty, int quantity, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maxQty = maxQty;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() { // Add this getter
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) { // Add this setter
        this.imageUrl = imageUrl;
    }
}
