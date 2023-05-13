package model;

public class SupplierForAProduct {
    private String name, reference ,status;
    private double productPrice;
    private int deliveryDelayDays;

    public SupplierForAProduct(String name, String reference, String status, double productPrice, int deliveryDelayDays) {
        setName(name);
        setReference(reference);
        setStatus(status);
        setProductPrice(productPrice);
        setDeliveryDelayDays(deliveryDelayDays);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProductPrice(double productPrice) {
        if (productPrice < 0) {
            this.productPrice = 0;
        }else {
            this.productPrice = productPrice;
        }
    }

    public void setDeliveryDelayDays(int deliveryDelayDays) {
        if (deliveryDelayDays < 0) {
            this.deliveryDelayDays = 0;
        }else {
            this.deliveryDelayDays = deliveryDelayDays;
        }
    }


    public String getName() {
        return name;
    }

    public String getReference() {
        return reference;
    }

    public String getStatus() {
        return status;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getDeliveryDelayDays() {
        return deliveryDelayDays;
    }
}
