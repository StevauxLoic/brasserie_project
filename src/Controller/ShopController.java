package Controller;

import Business.ShopManager;
import model.Exeptions.DeleteExeption;
import model.Exeptions.ProductTypeExeption;
import model.Exeptions.SelectExeption;
import model.Exeptions.UpdateExeption;
import model.Product;
import model.ProductType;

import javax.sound.sampled.Port;
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
        return shopManager.getOneProuct(referenceOfTheProduct);
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
}
