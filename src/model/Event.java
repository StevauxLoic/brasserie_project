package model;

import java.util.Date;

public class Event {
    private String reference; // primary key
    private String label;
    private Date date;

    public Event(String reference, String label, Date date){
        this.reference = reference;
        this.label = label;
        this.date = date;
    }

}
