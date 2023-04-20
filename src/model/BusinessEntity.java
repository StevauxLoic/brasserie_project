package model;

public class BusinessEntity {
    private String reference; // primaryKey
    private String name;
    private Long vatNumber;
    private String statutReference; // foreignKey

    public BusinessEntity(String reference, String name, Long vatNumber, String statutReference){
        this.reference = reference;
        this.name = name;
        setVatNumber(vatNumber);
        this.statutReference = statutReference;
    }

    public void setVatNumber(Long vatNumber) {
        //
    }
}
