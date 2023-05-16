package dataAccess;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISearchData {
    ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws GetDatasException, CreateConnectionException;
    ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws GetDatasException, CreateConnectionException;
    ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws GetDatasException, CreateConnectionException;
    ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws GetDatasException, CreateConnectionException;
    ArrayList<Product> getAllProductOutOfMinimumStock () throws GetDatasException, CreateConnectionException;
    ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws GetDatasException, CreateConnectionException;
}
