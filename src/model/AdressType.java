package model;

public class AdressType {
    private Integer reference;
    private String label;

    public AdressType(Integer reference,String label){
        this.reference = reference;
        this.label = label;
    }

    public AdressType(String label){
        this(null, label);
    }

}
