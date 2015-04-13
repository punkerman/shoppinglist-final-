package com.redrex.alejandrobedoya.shoppinglist;

/**
 * Created by alejandro on 4/4/2015.
 */
public class Product {
    private String product;
    private String brand;
    private String price;
    private String relation;
    private String image;

    public void setProduct(String product) {
        this.product = product;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(String price) {
        this.price = price;
    }

public void setRelation(String relation) {this.relation=relation;}
    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct() {
        return product;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
    public String getRelation() {
        return relation;
    }
}
