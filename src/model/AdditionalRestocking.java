package model;

public class AdditionalRestocking {
    private int amount;
    private String productReference; // primarykey with eventRef & foreignKey
    private int eventReference; // primaryKey with productRef & foreignKey

    public AdditionalRestocking(int amount, String productReference, int eventReference){
        setProductReference(productReference);
        setEventReference(eventReference);
        setAmount(amount);
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public void setEventReference(int eventReference) {
        this.eventReference = eventReference;
    }


    public int getAmount() {
        return amount;
    }

    public String getProductReference() {
        return productReference;
    }

    public int getEventReference() {
        return eventReference;
    }
}
