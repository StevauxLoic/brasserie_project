package model;

public class Adress {
    private String reference; // primaryKey
    private String street;
    private  int number;
    private String businessEntityReference; // foreign key & non obligatory
    private int typeReference; // foreignKey
    private String cityReference; // foreignKey

    public Adress(String reference, String street, int number, String businessEntityReference, int typeReference, String cityReference){
        setReference(reference);
        setStreet(street);
        setNumber(number);
        setBusinessEntityReference(businessEntityReference);
        setTypeReference(typeReference);
        setCityReference(cityReference);
    }


    public void setNumber(int number){
        if(number < 1){
            this.number = 1;
        } else {
            this.number = number;
        }
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTypeReference(int typeReference) {
        this.typeReference = typeReference;
    }

    public void setCityReference(String cityReference) {
        this.cityReference = cityReference;
    }

    public void setBusinessEntityReference(String businessEntityReference) {
        this.businessEntityReference = businessEntityReference;
    }
}
