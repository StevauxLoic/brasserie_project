package model;

import Controller.ShopController;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProductSupplementDueToEvent {
    private LocalDate startingDate, endingDate;
    private ArrayList<Product> productsWithAdditionalRestockingInADelay;
    private ShopController controller;

    public ProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate){
        setStartingDate(startingDate);
        setEndingDate(endingDate);
        controller = new ShopController();
        setProductsWithAdditionalRestockingInADelay(startingDate, endingDate);
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public void setProductsWithAdditionalRestockingInADelay(LocalDate startingDate, LocalDate endingDate) {
        this.productsWithAdditionalRestockingInADelay = controller.;
    }

    public ArrayList<Product> getProductsWithAdditionalRestockingInADelay() {
        return productsWithAdditionalRestockingInADelay;
    }
}

