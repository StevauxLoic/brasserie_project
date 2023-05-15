package dataAccess;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISearchData {
    ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws SelectException, CreateConnectionException;
    ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectException, CreateConnectionException;
    ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws SelectException, CreateConnectionException;
    ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws SelectException, CreateConnectionException;
    ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectException, CreateConnectionException;
}
