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

    public void setProductReference(String productReference) {
        if(productReference != null){
            this.productReference = productReference;
        } else {
            // throws erreur
        }
    }

    public void setEventReference(int eventReference){
        /*
        if(dans la bd){
            this.eventReference = eventReference;
        } else {
            creer ou erreur
        }
        */
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
