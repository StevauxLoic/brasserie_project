package model;

public class DocumentType {
    private Integer reference; // primary key
    private String label;


    public DocumentType(int reference, String label){
        setReference(reference);
        setLabel(label);
    }

    public void setLabel(String label) {
        if(label != null){
            this.label = label;
        } else {
            // throws exept
        }
    }

    public void setReference(int reference) {
        /*
        if(reference est dans bd){
            this.reference = reference;
        } else {
            // creer ou throws erreur
        }
        */
    }
}
