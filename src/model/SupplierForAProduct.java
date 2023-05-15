package model;

import model.Exeptions.SupplierForAProductException;

public class SupplierForAProduct {
    private String name, reference ,status;
    private double productPrice;
    private int deliveryDelayDays;

    public SupplierForAProduct(String name, String reference, String status, double productPrice, int deliveryDelayDays) throws SupplierForAProductException {
        setName(name);
        setReference(reference);
        setStatus(status);
        setProductPrice(productPrice);
        setDeliveryDelayDays(deliveryDelayDays);
    }

    public void setName(String name) throws SupplierForAProductException {
        if (name != null) {
            this.name = name;
        } else {
            throw new SupplierForAProductException("le nom donné est null");
        }
    }

    public void setReference(String reference) throws SupplierForAProductException {
        if (reference != null) {
            this.reference = reference;
        } else {
            throw new SupplierForAProductException("la référence donné est null");
        }
    }

    public void setStatus(String status) throws SupplierForAProductException {
        if (status != null) {
            this.status = status;
        } else {
            throw new SupplierForAProductException("le status donné est null");
        }
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
