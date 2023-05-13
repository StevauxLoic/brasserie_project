package model;

import java.util.Date;

public class Event {
    private Integer reference; // primary key
    private String label;
    private Date date;

    public Event(Integer reference, String label, Date date){
        setReference(reference);
        setLabel(label);
        setDate(date);
    }

    public Event(String label, Date date){
        this(null, label, date);
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLabel(String label){
        if(label != null){
            this.label = label;
        } else {
            this.label = null;
        }
    }

}
