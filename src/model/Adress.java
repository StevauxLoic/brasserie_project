package model;

public class Adress {
    private String reference; // primaryKey
    private String street;
    private  int number;
    private String businessEntityReference; // foreign key & non obligatory
    private String typeReference; // foreignKey
    private String cityReference; // foreignKey

    public Adress(String reference, String street, int number, String businessEntityReference, String typeReference, String cityReference){
        this.reference = reference;
        this.street = street;
        setNumber(number);
        // businessEntityRef
        this.typeReference = typeReference;
        this.cityReference = cityReference;
    }

    public Adress(String reference, String street, int number, String typeReference, String cityReference){
        this(reference, street, number, null /*here is the businessEntityRef*/, typeReference, cityReference);
    }

    public void setNumber(int number){
        if(number < 1){
            this.number = 1;
        } else {
            this.number = number;
        }
    }
}
