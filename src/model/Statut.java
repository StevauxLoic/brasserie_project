package model;

public class Statut {
    private String reference;
    private String label;
    private Boolean mustPayDeposit;
    private Double creditLimitation;
    private Boolean discount;

    public Statut(String reference, String label){
        setReference(reference);
        setLabel(label);
    }

    public void setReference(String reference) {
       this.reference = reference;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public void setMustPayDeposit(Boolean mustPayDeposit) {
        this.mustPayDeposit = mustPayDeposit;
    }

    public void setCreditLimitation(Double creditLimitation) {
        if(creditLimitation < 0){
            this.creditLimitation = 0.0;
        } else {
            this.creditLimitation = creditLimitation;
        }
    }


    public void setLabel(String label) {
        this.label = label;
    }
}
