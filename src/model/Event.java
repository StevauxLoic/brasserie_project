package model;

import java.util.Date;

public class Event {
    private Integer reference; // primary key
    private String label;
    private Date date;

    public Event(Integer reference, String label, Date date){
        this.reference = reference;
        setLabel(label);
        this.date = date;
    }

    public Event(String label, Date date){
        this(null, label, date);
    }



    public void setLabel(String label){
        if(label != null){
            this.label = label;
        } else {
            this.label = null;
        }
    }

}
