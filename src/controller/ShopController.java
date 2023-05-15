package controller;

import business.ShopManager;
import model.*;
import model.Exeptions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShopController {
    private ShopManager shopManager;

    public ShopController() {
        this.shopManager = new ShopManager();
    }

    public void closeConnection() throws CloseConnectionException, CreateConnectionException{
        shopManager.closeConnection();
    }

    public ArrayList<Product> getAllProduct() throws SelectException, CreateConnectionException{
        return shopManager.getAllProduct();
    }

    public void updateProduct(Product productToUpdate) throws UpdateException, CreateConnectionException{
        shopManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteException, CreateConnectionException{
        shopManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws CreateConnectionException, SelectException {
        return shopManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateException, CreateConnectionException{
        shopManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectException, CreateConnectionException{
        return shopManager.getAllBusinessEntities();
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectException, CreateConnectionException{
        return shopManager.getAllAdressesOfBusinessEntity(businessEntity);
    }

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws SelectException,CreateConnectionException{
        return shopManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectException,CreateConnectionException{
        return shopManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws SelectException, CreateConnectionException{
        return shopManager.getAllAdressesOfABusinessEntity(businessEntity);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws SelectException, CreateConnectionException{
        return shopManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock () throws SelectException, CreateConnectionException{
        return shopManager.getAllProductOutOfMinimumStock();
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectException, CreateConnectionException{
        return shopManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }
}
