package Controller;

import Business.ShopManager;
import model.*;
import model.Exeptions.*;

import javax.sound.sampled.Port;
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

    public ArrayList<Product> getAllProduct() throws SelectExeption, CreateConnectionException{
        return shopManager.getAllProduct();
    }

    public Product getOneProduct(String referenceOfTheProduct) throws SelectExeption, CreateConnectionException{
        return shopManager.getOneProduct(referenceOfTheProduct);
    }

    public void updateProduct(Product productToUpdate) throws UpdateExeption, CreateConnectionException{
        shopManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteExeption, CreateConnectionException{
        shopManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption, CreateConnectionException{
        return shopManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateExeption, CreateConnectionException{
        shopManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectExeption, CreateConnectionException{
        return shopManager.getAllBusinessEntities();
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectExeption, CreateConnectionException{
        return shopManager.getAllAdressesOfBusinessEntity(businessEntity);
    }

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption,CreateConnectionException{
        return shopManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption,CreateConnectionException{
        return shopManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption, CreateConnectionException{
        return shopManager.getAllAdressesOfABusinessEntity(businessEntityId);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption, CreateConnectionException{
        return shopManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectExeption, CreateConnectionException{
        return shopManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }
}
