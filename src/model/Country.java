package model;

public class Country {
    private String reference; // primary key
    private String label;

    public Country(String reference, String label){
        this.reference = reference;
        this.label = label;
    }
}
