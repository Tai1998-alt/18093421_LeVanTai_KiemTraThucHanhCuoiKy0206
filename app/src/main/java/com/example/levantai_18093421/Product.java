package com.example.levantai_18093421;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String type;
    private double price;
    private String country;

    public Product() {
    }

    public Product(int id, String type, double price, String country) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
