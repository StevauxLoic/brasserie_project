package Business;

import DataAccess.BusinessEntityData;
import DataAccess.ProductData;
import model.Adress;
import model.BusinessEntity;
import model.Exeptions.*;
import model.Product;
import model.ProductType;

import java.util.ArrayList;

public class ShopManager {
    private ProductData productDataManager;
    private BusinessEntityData businessEntityDataManager;

    public ShopManager(){
        this.productDataManager = new ProductData();
        this.businessEntityDataManager = new BusinessEntityData();
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

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectExeption{
        return businessEntityDataManager.getAllBusinessEntities();
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectExeption{
        return businessEntityDataManager.getAllAdressesOfBusinessEntity(businessEntity);
    }



}
