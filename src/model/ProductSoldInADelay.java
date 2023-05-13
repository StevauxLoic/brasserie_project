package model;

import Controller.ShopController;
import model.Exeptions.SelectExeption;

import java.time.LocalDate;

public class ProductSoldInADelay {
    private String name;
    private double costPrice;
    private int quantity;

    public ProductSoldInADelay(String name, double costPrice, int quantity){
        setName(name);
        setCostPrice(costPrice);
        setQuantity(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getName() {
        return name;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
