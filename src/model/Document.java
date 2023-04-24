package model;

import java.util.Date;

public class Document {
    private int reference; // primaryKey
    private Date date;
    private Double discount; // non
    private Date deadLine; // non
    private Boolean isPaid; // non
    private Date validityDate; // non
    private Boolean isDelivered; //non

    private Date deliveryDate; // non
    private Double deposit; // non
    private Double additionalFees; // non
    private int documentTypeReference; // foreignKey
    private String deliveryAdressReference; // foreignKey && non

    public Document(int reference, Date date, Double discount, Date deadLine, Boolean isPaid, Date validityDate, Boolean isDelivered, Date deliveryDate, Double deposit, Double additionalFees, int documentTypeReference, String deliveryAdressReference){
        this.reference = reference;
        setDate(date);
        this.documentTypeReference = documentTypeReference;
        setDiscount(discount);
        setDeadLine(deadLine);
        this.isPaid = isPaid;
        setValidityDate(validityDate);
        this.isDelivered = isDelivered;
    }

    public void setDate(Date date) {
        if(date != null /*&& date <= dateDuJour */){
            this.date = date;
        } else {
            // lancer une exeption
        }
    }

    public void setDiscount(Double discount) {
        if(discount == null || discount > 0.0){
            this.discount = discount;
        } else {
            this.discount = 0.0;
        }
    }

    public void setDeadLine(Date deadLine) {
        // https://docs.oracle.com/javase/8/docs/api/java/util/Date.html#compareTo-java.util.Date-
        if(deadLine == null || deadLine.compareTo(this.date) > 0){
            this.deadLine = deadLine;
        } else {
            // throw exept
        }
    }

    public void setValidityDate(Date validityDate) {
        if(validityDate == null || validityDate.compareTo(this.date) > 0){
            this.validityDate = validityDate;
        } else {
            // throw exept
        }
    }

    public void setDeliveryDate(Date deliveryDate) {
        if(deliveryDate == null || deliveryDate.compareTo(this.date) > 0){
            this.deliveryDate = deliveryDate;
        } else {
            // throw exeption
        }
    }
}
