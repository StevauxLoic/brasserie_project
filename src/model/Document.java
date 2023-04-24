package model;

import java.time.LocalDate;
import java.util.Date;

public class Document {
    private int reference; // primaryKey
    private LocalDate date;
    private Double discount; // non
    private LocalDate deadLine; // non
    private Boolean isPaid; // non
    private LocalDate validityDate; // non
    private Boolean isDelivered; //non
    private LocalDate deliveryDate; // non
    private Double deposit; // non
    private Double additionalFees; // non
    private int documentTypeReference; // foreignKey
    private String deliveryAdressReference; // foreignKey && non

    public Document(LocalDate date, Double discount, LocalDate deadLine, Boolean isPaid, LocalDate validityDate, Boolean isDelivered, LocalDate deliveryDate, Double deposit, Double additionalFees, int documentTypeReference, String deliveryAdressReference){
        setDocumentTypeReference(documentTypeReference);
        setDate(date);
        this.documentTypeReference = documentTypeReference;
        this.deliveryAdressReference = deliveryAdressReference;
        setDiscount(discount);
        setDeadLine(deadLine);
        this.isPaid = isPaid;
        setValidityDate(validityDate);
        this.isDelivered = isDelivered;
        setDeliveryDate(deliveryDate);
        setDeposit(deposit);
        setAdditionalFees(additionalFees);
    }

    public void setDocumentTypeReference(int documentTypeReference) {
        /*
        if(documentTypeReference appartient a la bd){
            this.documentTypeReference = documentTypeReference;
        } else {
            throw erreur
        }
         */
    }

    public void setDate(LocalDate date) {
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

    public void setDeadLine(LocalDate deadLine) {
        // https://docs.oracle.com/javase/8/docs/api/java/util/Date.html#compareTo-java.util.Date-
        if(deadLine == null || deadLine.isAfter(date)){
            this.deadLine = deadLine;
        } else {
            // throw exept
        }
    }

    public void setValidityDate(LocalDate validityDate) {
        if(validityDate == null || validityDate.isAfter(date)){
            this.validityDate= validityDate;
        } else {
            // throw exept
        }
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        if(deliveryDate== null || deliveryDate.isAfter(date)){
            this.deliveryDate = deliveryDate;
        } else {
            // throw exeption
        }
    }

    public void setDeposit(Double deposit) {
        if(deposit == null || deposit >= 0){
            this.deposit = deposit;
        } else {
            this.deposit = 0.0;
        }
    }

    public void setAdditionalFees(Double additionalFees) {
        if(additionalFees <= 0){
            this.additionalFees = 0.0;
        } else {
            this.additionalFees = additionalFees;
        }

    }
}
