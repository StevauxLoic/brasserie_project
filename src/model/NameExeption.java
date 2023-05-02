package model;

public class NameExeption extends Exception{
    private String wrongName;

    public NameExeption(String wrongName, String message){
        super(message);
        this.wrongName = wrongName;
    }

    public String getWrongName() {
        return wrongName;
    }
}
