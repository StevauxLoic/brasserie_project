package model;

public class BusinessEntityAdress {
    private String country, city, street, typeName;
    private int zipCode, houseNumber;

    public BusinessEntityAdress(String country, String city, String street, int zipCode, int houseNumber, String typeName) {
        setCountry(country);
        setCity(city);
        setStreet(street);
        setZipCode(zipCode);
        setHouseNumber(houseNumber);
        setTypeName(typeName);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getTypeName() {
        return typeName;
    }
}
