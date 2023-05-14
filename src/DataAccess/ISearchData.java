package DataAccess;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectExeption;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISearchData {
    ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption, CreateConnectionException;
    ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption, CreateConnectionException;
    ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption, CreateConnectionException;
    ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption, CreateConnectionException;
    ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectExeption, CreateConnectionException;
}
