package model;

import java.time.LocalDate;

public class Product {
    private String reference; // primaryKey
    private int typeReference; // foreignKey
    private String name;
    private double vat;
    private int quantityInStock;
    private int minimumQuantityInStock;
    private boolean isSparkling;
    private double alcoholLevel;
    private LocalDate launchingDate;
    private double price;
    private String description; // non obligatory

    public Product(String reference, int typeReference,String name, double vat, int minimumQuantityInStock, boolean isSparkling, LocalDate launchingDate, double price, double alcoholLevel, int quantityInStock, String description){
           setTypeReference(typeReference);
           setReference(reference);
           setName(name);
           setVat(vat);
           setQuantityInStock(quantityInStock);
           setMinimumQuantityInStock(minimumQuantityInStock);
           setSparkling(isSparkling);
           setPrice(price);
           setLaunchingDate(launchingDate);
           setAlcoholLevel(alcoholLevel);
           setDescription(description);
    }

    public Product(String reference, int typeReference,String name, double vat, int minimumQuantityInStock, boolean isSparkling, LocalDate launchingDate, double price, double alcoholLevel, int quantityInStock){
        this(reference, typeReference, name, vat, minimumQuantityInStock, isSparkling, launchingDate, price, alcoholLevel, quantityInStock,null);
    }

    public void setTypeReference(int typeReference){
        this.typeReference = typeReference;

    }

    public void setLaunchingDate(LocalDate launchingDate) {
        this.launchingDate = launchingDate;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setName(String name) {
        this.name = name;
    }

    // the paramater here is a pourcentage -> if(32.5%) then parameter = 32.5
    public void setVat(double vat){
        if(vat <= 0){
            this.vat = 0;
        } else {
            if(vat >= 100){
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
            if(alcoholLevel >= 100){
                this.alcoholLevel = 100.0;
            } else {
                this.alcoholLevel = alcoholLevel;
            }
        }
    }

    public void setSparkling(boolean isSparkling) {
        this.isSparkling = isSparkling;
    }

    public void setQuantityInStock(int quantityInStock){
        if (quantityInStock >= 0 ) {
            this.quantityInStock = quantityInStock;
        } else {
            this.quantityInStock = 0;
        }
    }

    public void setMinimumQuantityInStock(int minimumQuantityInStock){
        if(minimumQuantityInStock < 0){
            this.minimumQuantityInStock = 0;
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

    public void setDescription(String description) {
        if (description != null && description.length() > 0) {
            this.description = description;
        } else {
            this.description = null;
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

    public LocalDate getLaunchingDate() {
        return launchingDate;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
