package model;

public class ProductSupplementDueToEvent {
    private String eventName, productTypeName, productName,productReference;
    private int minimumQuantityInStock, amount;

    public ProductSupplementDueToEvent(String eventName, String productTypeName, String productName, String productReference, int minimumQuantityInStock, int amount){
        setEventName(eventName);
        setProductTypeName(productTypeName);
        setProductName(productName);
        setProductReference(productReference);
        setMinimumQuantityInStock(minimumQuantityInStock);
        setAmount(amount);
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    public void setMinimumQuantityInStock(int minimumQuantityInStock) {
        if (minimumQuantityInStock < 0) {
            this.minimumQuantityInStock = 0;
        } else {
            this.minimumQuantityInStock = minimumQuantityInStock;
        }
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAmount() {
        return amount;
    }

    public int getMinimumQuantityInStock() {
        return minimumQuantityInStock;
    }

    public String getProductReference() {
        return productReference;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }
}

