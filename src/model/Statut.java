package model;

public class Statut {
    private String reference;
    private String label;
    private Boolean mustPayDeposit;
    private Double creditLimitation;
    private Boolean discount;

    public Statut(String reference, String label, Boolean mustPayDeposit, Double creditLimitation, Boolean discount){
        setReference(reference);
        setLabel(label);
        this.mustPayDeposit = mustPayDeposit;
        setCreditLimitation(creditLimitation);
        this.discount = discount;
    }

    public void setReference(String reference) {
        if(reference != null){
            this.reference = reference;
        } else {
            // throw exept
        }

    }

    public void setCreditLimitation(Double creditLimitation) {
        if(creditLimitation < 0){
            this.creditLimitation = 0.0;
        } else {
            this.creditLimitation = creditLimitation;
        }
    }


    public void setLabel(String label) {
        if(label != null){
            this.label = label;
        } else {
            // throw Erreur
        }
    }
}
