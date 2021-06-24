package com.revature.entities;

public class Product {
    private int productId;
    private String name;
    private String description;
    private float price;
    private String mood;
    private String category;

    public Product(){}

    public Product(int productId, String name, String description, float price, String mood, String category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.mood = mood;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", mood='" + mood + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
