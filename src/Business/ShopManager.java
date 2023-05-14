package Business;

import DataAccess.*;
import model.*;
import model.Exeptions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ShopManager {
    private ProductData productDataManager;
    private BusinessEntityData businessEntityDataManager;
    private SearchData searchDataManager;
    private ProductTypeData productTypeDataManager;
    private CloseDataAccessConnection closeDataAccessConnectionManager;


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

    public ArrayList<Product> getAllProduct() throws SelectExeption, CreateConnectionException{
        return productDataManager.getAllProducts();
    }

    public Product getOneProduct(String referenceOfTheProduct) throws SelectExeption, CreateConnectionException{
        return productDataManager.getOneProduct(referenceOfTheProduct);
    }

    public void updateProduct(Product productToUpdate) throws UpdateExeption, CreateConnectionException {
        productDataManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteExeption, CreateConnectionException {
        productDataManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption, CreateConnectionException{
        return productTypeDataManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateExeption, CreateConnectionException{
        productDataManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectExeption, CreateConnectionException{
        return businessEntityDataManager.getAllBusinessEntities();
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectExeption, CreateConnectionException{
        return businessEntityDataManager.getAllAdressesOfBusinessEntity(businessEntity);
    }

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption, CreateConnectionException{
        return searchDataManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption, CreateConnectionException{
        return searchDataManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption, CreateConnectionException{
        return searchDataManager.getAllAdressesOfABusinessEntity(businessEntityId);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption, CreateConnectionException{
        return searchDataManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectExeption, CreateConnectionException{
        return searchDataManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }

}
