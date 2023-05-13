package Controller;

import Business.ShopManager;
import model.Adress;
import model.BusinessEntity;
import model.Exeptions.*;
import model.Product;
import model.ProductType;

import javax.sound.sampled.Port;
import java.time.LocalDate;
import java.util.ArrayList;

public class ShopController {
    private ShopManager shopManager;

    public ShopController(){
        this.shopManager = new ShopManager();
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

    public int countQuantitySoldOfProduct(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption{
        return shopManager.countQuantitySoldOfProduct(startingDate, endingDate, productType);
    }

}
