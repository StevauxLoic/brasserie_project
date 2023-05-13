package model;

public class City {
    private String reference; // primaryKey
    private String label;
    private int zipCode;
    private String countryReference; // foreignKey

    public City(String reference, String label, int zipCode, String countryReference){
        setReference(reference);
        setLabel(label);
        setZipCode(zipCode);
        setCountryReference(countryReference);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountryReference(String countryReference) {
        this.countryReference = countryReference;
    }
}
