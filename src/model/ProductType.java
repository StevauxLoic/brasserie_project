package model;

public class ProductType {
    private Integer reference;
    private String label;

    public ProductType(Integer reference, String label){
        this.reference = reference;
        this.label = label;
    }

    public ProductType(String label){
        this(null, label);
    }


}
