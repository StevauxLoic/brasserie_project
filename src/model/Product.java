package model;

import java.util.Date;

public class Product {
    private String reference; // primaryKey
    private String typeReference; // foreignKey
    private String name;
    private double vat;
    private int quantityInStock;
    private int minimumQuantityInStock;
    private boolean isSparkling;
    private double alcoholLevel; // non obligatory
    private Date launchingDate;
    private double price;

    public Product(String reference, String typeReference, String name, double vat, int minimumQuantityInStock, boolean isSparkling, Date launchingDate, double price, double alcoholLevel){
        // creation reference
        // type ref le faire peut etre avec 1 enum pour ne pas avoir d'erreur
        this.name = name;
        setVat(vat);
        setQuantityInStock();
        setMinimumQuantityInStock(minimumQuantityInStock);
        setPrice(price);
        // je sais pas comment faire le date
        setAlcoholLevel(alcoholLevel);
    }

    // second constructor to create a soft drink
    public Product(String reference, String typeReference, String name, double vat, int quantityInStock, int minimumQuantityInStock, boolean isSparkling, Date launchingDate, double price){
        this(reference, typeReference, name, vat, minimumQuantityInStock, isSparkling, launchingDate, price, 0);
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
        if(alcoholLevel < 0){
            this.alcoholLevel = 0;
        } else {
            if(alcoholLevel > 100){
                this.alcoholLevel = 100;
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


}
