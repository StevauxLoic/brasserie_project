package model;

public class DetailsLine {
    private double price;
    private int quantity;
    private int anomaliesQuantity;
    private  double vat;
    private Double discount;
    private String productReference; // foreignKey
    private int documentReference; // foreignKey

    public DetailsLine(int quantity, String productReference, int documentReference){
        setQuantity(quantity);
        this.productReference = productReference;
        this.documentReference = documentReference;
    }

    public void setPrice(double price) {
        if(price <= 0){
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public void setQuantity(int quantity) {
        if(quantity <= 1){
            this.quantity = 1;
        } else {
            this.quantity = quantity;
        }
    }

    public void setAnomaliesQuantity(int anomaliesQuantity) {
        if(anomaliesQuantity <= 0){
            this.anomaliesQuantity = 0;
        } else {
            this.anomaliesQuantity = anomaliesQuantity;
        }
    }

    public void setVat(double vat) {
        if(vat <= 0){
            this.vat = 0;
        } else {
            if(vat >= 100)
                this.vat = 100;
            else
                this.vat = vat;
        }
    }

    public void setDiscount(Double discount) {
        if(discount <= 0 || discount == null){
            this.discount = null;
        } else {
            this.discount = discount;
        }
    }
}
