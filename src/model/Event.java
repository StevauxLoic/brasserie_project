package model;

import java.util.Date;

public class Event {
    private int reference; // primary key
    private String label;
    private Date date;

    public Event(int reference, String label, Date date){
        setReference(reference);
        setLabel(label);
        this.date = date;
    }

    public void setReference(int reference) {
        /*
        if(reference est dans al bd){
            this.reference = reference;
        } else {
            erreur ou creer
        }
        */
    }

    public void setLabel(String label){
        if(label != null){
            this.label = label;
        } else {
            this.label = null;
        }
    }

}
