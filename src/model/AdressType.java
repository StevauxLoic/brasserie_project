package model;

public class AdressType {
    private int reference;
    private String label;

    public AdressType(String label){
        setReference();
        setLabel(label);
    }

    public void setReference() {
        // recherche dans la base de donnée car c'est un auto implément
    }

    public void setLabel(String label) {
        if(label != null){
            this.label = label;
        } else {
            // exeption ?
        }
    }
}
