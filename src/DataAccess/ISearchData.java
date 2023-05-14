package DataAccess;

import model.*;
import model.Exeptions.SelectExeption;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISearchData {
    ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption;
    ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption;
    ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption;
    ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption;
    ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectExeption;
}
