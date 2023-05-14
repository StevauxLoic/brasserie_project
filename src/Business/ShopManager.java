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


    public ShopManager() throws CreateConnectionException {
        this.productDataManager = new ProductData();
        this.businessEntityDataManager = new BusinessEntityData();
        this.searchDataManager = new SearchData();
    }

    public void closeConnection() throws CloseConnectionException{
        SingletonConnection.closeConnection();
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

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption {
        return searchDataManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption{
        return searchDataManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption{
        return searchDataManager.getAllAdressesOfABusinessEntity(businessEntityId);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption{
        return searchDataManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectExeption{
        return searchDataManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }

}
