package model;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

// TODO montrer que une seule exeption

public class Product {
    private String reference; // primaryKey
    private Integer typeReference; // foreignKey
    private String name;
    private double vat;
    private int quantityInStock;
    private int minimumQuantityInStock;
    private boolean isSparkling;
    private double alcoholLevel;
    private LocalDate launchingDate;
    private double price;
    private String description; // non obligatory

    public Product(String reference, Integer typeReference,String name, double vat, int minimumQuantityInStock, boolean isSparkling, LocalDate launchingDate, double price, double alcoholLevel, String description, int quantityInStock) throws ReferenceExeption , TypeExeption, NameExeption, DateExeption {
        setTypeReference(typeReference);
        setReference(reference);
        setName(name);
        setVat(vat);
        setQuantityInStock(quantityInStock);
        setMinimumQuantityInStock(minimumQuantityInStock);
        this.isSparkling = isSparkling;
        setPrice(price);
        setLaunchingDate(launchingDate);
        setAlcoholLevel(alcoholLevel);
    }

    public void setTypeReference(Integer typeReference) throws TypeExeption{
        if(typeReference <= 0 ){//||typeReference >= /* auto increment*/){ TODO revoir la règle ici
            String message = "le type numero : " + typeReference + " n'est pas un type existants";
            throw new TypeExeption(typeReference, message);
        } else {
            this.typeReference = typeReference;
        }

    }

    public void setLaunchingDate(LocalDate launchingDate) throws DateExeption{
        if(launchingDate != null && launchingDate.isBefore(LocalDate.now())){
            this.launchingDate = launchingDate;
        } else {
            String message = "La date selectionner (" + launchingDate + ") n'est pas encore existante soit vous devez en mettre une";
            throw new DateExeption(launchingDate, message);
        }
    }

    public void setReference(String reference) throws ReferenceExeption{
        if(reference != null){
            this.reference = reference;
        } else {
            String message = "reference null";
            throw new ReferenceExeption(reference, message);
        }
    }

    public void setName(String name) throws NameExeption{
        if(name != null){
            this.name = name;
        } else {
            String message = "Vous devez ajouter un nom";
            throw new NameExeption(name, message);
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

    public void setQuantityInStock(int quantityInStock){
        if (quantityInStock >= 0 ) {
            this.quantityInStock = quantityInStock;
        }
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

    public void setDescription(String description) {
        this.description = description;
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
