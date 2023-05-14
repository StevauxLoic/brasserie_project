package Controller;

import Business.ShopManager;
import model.*;
import model.Exeptions.*;

import javax.sound.sampled.Port;
import java.time.LocalDate;
import java.util.ArrayList;

public class ShopController {
    private ShopManager shopManager;

    public ShopController() throws CreateConnectionException {
        this.shopManager = new ShopManager();
    }

    public void closeConnection() throws CloseConnectionException {
        shopManager.closeConnection();
    }

    public ArrayList<Product> getAllProduct() throws SelectExeption{
        return shopManager.getAllProduct();
    }

    public Product getOneProduct(String referenceOfTheProduct) throws SelectExeption{
        return shopManager.getOneProduct(referenceOfTheProduct);
    }

    public void updateProduct(Product productToUpdate) throws UpdateExeption{
        shopManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteExeption{
        shopManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption{
        return shopManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateExeption{
        shopManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectExeption{
        return shopManager.getAllBusinessEntities();
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectExeption{
        return shopManager.getAllAdressesOfBusinessEntity(businessEntity);
    }

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption{
        return shopManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption{
        return shopManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption{
        return shopManager.getAllAdressesOfABusinessEntity(businessEntityId);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption{
        return shopManager.getAllProductOutOfMinimumStock(productType);
    }

}
