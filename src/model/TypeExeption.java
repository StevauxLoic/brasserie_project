package model;

public class TypeExeption extends Exception{
    private int wrongType;

    public TypeExeption(int wrongType, String message){
        super(message);
        this.wrongType = wrongType;
    }

    public int getWrongType() {
        return wrongType;
    }
}
