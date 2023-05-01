package model;

import java.time.LocalDate;

public class DateExeption extends Exception{
    private LocalDate wrongDate;

    public DateExeption(LocalDate wrongDate, String message){
        super(message);
        this.wrongDate = wrongDate;
    }

    public LocalDate getWrongDate() {
        return wrongDate;
    }
}
