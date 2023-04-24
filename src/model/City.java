package model;

public class City {
    private String reference; // primaryKey
    private String label;
    private int zipCode;
    private String countryReference; // foreignKey

    public City(String reference, String label, int zipCode, String countryReference){
        this.reference = reference;
        this.label = label;
        this.zipCode = zipCode;
        this.countryReference = countryReference;
    }
}
