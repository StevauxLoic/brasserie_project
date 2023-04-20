package model;

import java.security.ProtectionDomain;

public class ProductType {
    private int reference; // foreign key
    private String label;

    public ProductType(int reference, String label){
        setReference(reference);
        this.label = label;
    }

    public void setReference(int reference) {
        // c'est censé être un auto incrémentation
    }
}
