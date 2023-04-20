package model;

public class AdditionalRestocking {
    private int amount;
    private String productReference; // primarykey with eventRef & foreignKey
    private String eventReference; // primaryKey with productRef & foreignKey

    public AdditionalRestocking(int amount, String productReference, String eventReference){
        setProductReference(productReference);
        setEventReference(eventReference);
        setAmount(amount);
    }

    public void setProductReference(String productReference) {
        if(productReference != null){
            this.eventReference = eventReference;
        } else {
            this.eventReference = null;
        }
    }

    public void setEventReference(String eventReference){
        if(eventReference != null){
            this.eventReference = eventReference;
        } else {
            this.eventReference = null;
        }
    }

    public void setAmount(int amount){
        // 10 is an absurd value
        if(amount <= 10){
            this.amount = 10;
        } else {
            this.amount = amount;
        }
    }
}
