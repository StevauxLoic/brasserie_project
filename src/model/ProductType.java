package model;

public class ProductType {
    private Integer reference;
    private String label;

    public ProductType(Integer reference, String label){
        setReference(reference);
        setLabel(label);
    }

    public ProductType(String label){
        this(null, label);
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
