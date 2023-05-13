package model;

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
        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPrice(double costPrice) {
        if (costPrice < 0) {
            this.costPrice = 0;
        } else {
            this.costPrice = costPrice;
        }
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
