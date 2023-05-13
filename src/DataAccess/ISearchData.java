package DataAccess;

import model.Exeptions.SelectExeption;
import model.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISearchData {
    ArrayList<Product> getProductsWithAdditionalRestockingInADelay(LocalDate startingDate, LocalDate endingDate) throws SelectExeption;
    int countQuantitySoldOfProduct(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption;
}
