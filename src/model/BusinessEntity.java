package model;

public class BusinessEntity {
    private String reference; // primaryKey
    private String name;
    private Long vatNumber;
    private String statutReference; // foreignKey

    public BusinessEntity(String reference, String name, Long vatNumber, String statutReference){
        setReference(reference);
        setName(name);
        setVatNumber(vatNumber);
        setStatutReference(statutReference);
    }

    public void setReference(String reference){
        if(reference != null){
            this.reference = reference;
        } else {
            this.reference = null;
        }
    }


    public void setName(String name){
        if(name != null){
            this.name = name;
        } else {
            this.name = null;
        }
    }

    public void setStatutReference(String statutReference){
        if(statutReference != null){
            this.statutReference = statutReference;
        } else {
            this.statutReference = null;
        }
    }

    public void setVatNumber(Long vatNumber) {
        // obtention du formulaire
        if(vatNumber != null || vatNumber >=0){
            this.vatNumber = vatNumber;
        } else {
            this.vatNumber = null;
        }
    }

    public String getName() {
        return name;
    }

    public String getReference() {
        return reference;
    }
}
