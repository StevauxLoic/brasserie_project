package Business;

import DataAccess.ProductData;
import model.Exeptions.*;
import model.Product;
import model.ProductType;

import java.util.ArrayList;

public class ShopManager {
    private ProductData productDataManager;

    public ShopManager(){
        this.productDataManager = new ProductData();
    }

    public ArrayList<Product> getAllProduct() throws SelectExeption{
        return productDataManager.getAllProducts();
    }

    public Product getOneProduct(String referenceOfTheProduct) throws SelectExeption{
        return productDataManager.getOneProduct(referenceOfTheProduct);
    }

    public void updateProduct(Product productToUpdate) throws UpdateExeption {
        productDataManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteExeption {
        productDataManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption{
        return productDataManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateExeption{
        productDataManager.createProduct(productToCreate);
    }

}
