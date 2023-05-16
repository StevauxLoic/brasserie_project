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

    public ArrayList<Product> getAllProduct() throws GetDatasException, CreateConnectionException{
        return shopManager.getAllProduct();
    }

    public void updateProduct(Product productToUpdate) throws ModifyDatasException, CreateConnectionException{
        shopManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteDatasException, CreateConnectionException{
        shopManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws CreateConnectionException, GetDatasException {
        return shopManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateDatasException, CreateConnectionException{
        shopManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws GetDatasException, CreateConnectionException{
        return shopManager.getAllBusinessEntities();
    }


    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws GetDatasException,CreateConnectionException{
        return shopManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws GetDatasException,CreateConnectionException{
        return shopManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws GetDatasException, CreateConnectionException{
        return shopManager.getAllAdressesOfABusinessEntity(businessEntity);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws GetDatasException, CreateConnectionException{
        return shopManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock () throws GetDatasException, CreateConnectionException{
        return shopManager.getAllProductOutOfMinimumStock();
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws GetDatasException, CreateConnectionException{
        return shopManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }
}
