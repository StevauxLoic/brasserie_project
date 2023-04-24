package model;

import java.util.Date;

public class Document {
    private int reference; // primaryKey
    private Date date;
    private Double discount;
    private Date deadLine;
    private Boolean isPaid;
    private Date validityDate;
    private Boolean isDelivered;
    private Date deliveryDate;
    private Double deposit;
    private Double additionalFees;
    private int documentTypeReference; // foreignKey
    private String deliveryAdressReference; // foreignKey

    public Document(int reference, Date date, Double discount, Date deadLine, Boolean isPaid, Date validityDate, Boolean isDelivered, Date deliveryDate, Double deposit, Double additionalFees, int documentTypeReference, String deliveryAdressReference){
        this.reference = reference;

    }

}
