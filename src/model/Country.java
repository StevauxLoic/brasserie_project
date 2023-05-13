package model;

public class Country {
    private String reference; // primary key
    private String label;

    public Country(String reference, String label){
        setReference(reference);
        setLabel(label);
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
