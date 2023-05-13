package model;

public class AdressType {
    private Integer reference;
    private String label;

    public AdressType(Integer reference,String label){
        setReference(reference);
        setLabel(label);
    }

    public AdressType(String label){
        this(null, label);
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
