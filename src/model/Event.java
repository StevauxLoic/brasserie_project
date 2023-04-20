package model;

import java.util.Date;

public class Event {
    private String reference; // primary key
    private String label;
    private Date date;

    public Event(String reference, String label, Date date){
        setReference(reference);
        setLabel(label);
        this.date = date;
    }

    public void setReference(String reference){
        if(reference != null){
            this.reference = reference;
        } else {
            this.reference = null;
        }
    }

    public void setLabel(String label){
        if(label != null){
            this.label = label;
        } else {
            this.label = null;
        }
    }

}
