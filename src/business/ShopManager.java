package business;

import dataAccess.*;
import model.*;
import model.Exeptions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShopManager {
    private IProductData productDataManager;
    private IBusinessEntityData businessEntityDataManager;
    private ISearchData searchDataManager;
    private IProductTypeData productTypeDataManager;
    private ICloseDataConnection closeDataAccessConnectionManager;


    public ShopManager() {
        this.productDataManager = new ProductData();
        this.businessEntityDataManager = new BusinessEntityData();
        this.searchDataManager = new SearchData();
        productTypeDataManager = new ProductTypeData();
        closeDataAccessConnectionManager = new CloseDataAccessConnection();
    }

    public void closeConnection() throws CloseConnectionException, CreateConnectionException{
        closeDataAccessConnectionManager.closeConnection();
    }

    public ArrayList<Product> getAllProduct() throws GetDatasException, CreateConnectionException{
        return productDataManager.getAllProducts();
    }

    public void updateProduct(Product productToUpdate) throws ModifyDatasException, CreateConnectionException {
        productDataManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteDatasException, CreateConnectionException {
        productDataManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws CreateConnectionException, GetDatasException {
        return productTypeDataManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateDatasException, CreateConnectionException{
        productDataManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws GetDatasException, CreateConnectionException{
        return businessEntityDataManager.getAllBusinessEntities();
    }

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws GetDatasException, CreateConnectionException{
        return searchDataManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws GetDatasException, CreateConnectionException{
        return searchDataManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws GetDatasException, CreateConnectionException{
        return searchDataManager.getAllAdressesOfABusinessEntity(businessEntity);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws GetDatasException, CreateConnectionException{
        return searchDataManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock () throws GetDatasException, CreateConnectionException{
        return searchDataManager.getAllProductOutOfMinimumStock();
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws GetDatasException, CreateConnectionException{
        return searchDataManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }

}
