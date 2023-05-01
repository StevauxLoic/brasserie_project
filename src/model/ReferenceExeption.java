package model;

import java.sql.Ref;

public class ReferenceExeption extends Exception{
    private String wrongReference;

    public ReferenceExeption(String wrongReference, String message){
        super(message);
        this.wrongReference = wrongReference;
    }

    public String getWrongReference() {
        return wrongReference;
    }
}
