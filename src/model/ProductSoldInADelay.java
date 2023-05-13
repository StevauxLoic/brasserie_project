package model;

import Controller.ShopController;
import model.Exeptions.SelectExeption;

import java.time.LocalDate;

public class ProductSoldInADelay {
    private LocalDate startingDate, endingDate;
    private int productType;
    private int countOfSoldProduct;
    private ShopController controller;

    public ProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption{
        setStartingDate(startingDate);
        setEndingDate(endingDate);
        setProductType(productType);
        controller = new ShopController();
        setCountOfSoldProduct(startingDate, endingDate, productType);
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public void setCountOfSoldProduct(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption {
        countOfSoldProduct = controller.countQuantitySoldOfProduct(startingDate, endingDate, productType);
    }

    public int getCountOfSoldProduct() {
        return countOfSoldProduct;
    }
}
