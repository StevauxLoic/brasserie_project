package model;

import java.awt.*;
import java.util.Date;

public class Product {
    private String reference; // primaryKey
    private int typeReference; // foreignKey
    private String name;
    private double vat;
    private int quantityInStock;
    private int minimumQuantityInStock;
    private boolean isSparkling;
    private double alcoholLevel; // non obligatory
    private Date launchingDate;
    private double price;
    private String description;

    public Product(String reference, String typeReference, String name, double vat, int minimumQuantityInStock, boolean isSparkling, Date launchingDate, double price, double alcoholLevel, String description){
        // creation reference
        // type ref est un auto incrément a voir avec mySQL
        setReference(reference);
        setName(name);
        setVat(vat);
        setQuantityInStock();
        setMinimumQuantityInStock(minimumQuantityInStock);
        setPrice(price);
        // je sais pas comment faire le date
        setAlcoholLevel(alcoholLevel);
        this.description = description;
    }

    // second constructor to create an product with no despcription
    public Product(String reference, String typeReference, String name, double vat, int minimumQuantityInStock, boolean isSparkling, Date launchingDate, double price, double alcoholLevel){
        this(reference, typeReference, name, vat, minimumQuantityInStock, isSparkling, launchingDate, price,alcoholLevel, null);
    }

    public void setReference(String reference) {
        if(reference != null){
            this.reference = reference;
        } else {
            this.reference = null;
        }
    }

    public void setName(String name) {
        if(name != null){
            this.name = name;
        } else {
            this.name = null;
        }
    }

    // the paramater here is a pourcentage -> if(32.5%) then parameter = 32.5
    public void setVat(double vat){
        if(vat < 0){
            this.vat = 0;
        } else {
            if(vat > 100){
                this.vat = 100;
            } else {
                this.vat = vat;
            }
        }
    }

    public void setAlcoholLevel(double alcoholLevel){
        if(alcoholLevel <= 0){
            this.alcoholLevel = 0;
        } else {
            if(alcoholLevel > 100){
                this.alcoholLevel = 100.0; // accepte 100.0 mais pas 100 regarder pour moi le hard coder
            } else {
                this.alcoholLevel = alcoholLevel;
            }
        }
    }

    public void setQuantityInStock(){
        // do with the sql part to calculate how many we have
    }

    public void setMinimumQuantityInStock(int minimumQuantityInStock){
        // 10 is an absurd value, but seems correct to have a basic minimumQuantityInStock
        if(minimumQuantityInStock <= 10){
            this.minimumQuantityInStock = 10;
        } else {
            this.minimumQuantityInStock = minimumQuantityInStock;
        }
    }

    public void setPrice(double price){
        if(price < 0){
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public String getReference() {
        return reference;
    }

    public int getTypeReference() {
        return typeReference;
    }

    public String getName() {
        return name;
    }

    public double getVat() {
        return vat;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public int getMinimumQuantityInStock() {
        return minimumQuantityInStock;
    }

    public boolean isSparkling() {
        return isSparkling;
    }

    public Double getAlcoholLevel() {
        return alcoholLevel;
    }

    public Date getLaunchingDate() {
        return launchingDate;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
