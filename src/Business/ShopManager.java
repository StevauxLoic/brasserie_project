package Business;

import DataAccess.*;
import model.*;
import model.Exeptions.*;

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

    public ArrayList<Product> getAllProduct() throws SelectException, CreateConnectionException{
        return productDataManager.getAllProducts();
    }

    public Product getOneProduct(String referenceOfTheProduct) throws SelectException, CreateConnectionException{
        return productDataManager.getOneProduct(referenceOfTheProduct);
    }

    public void updateProduct(Product productToUpdate) throws UpdateException, CreateConnectionException {
        productDataManager.updateProduct(productToUpdate);
    }

    public void deleteProduct(Product productToDelete) throws DeleteException, CreateConnectionException {
        productDataManager.deleteProduct(productToDelete);
    }

    public ArrayList<ProductType> getAllProductType() throws CreateConnectionException, SelectException {
        return productTypeDataManager.getAllProductType();
    }

    public void createProduct(Product productToCreate) throws CreateException, CreateConnectionException{
        productDataManager.createProduct(productToCreate);
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectException, CreateConnectionException{
        return businessEntityDataManager.getAllBusinessEntities();
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectException, CreateConnectionException{
        return businessEntityDataManager.getAllAdressesOfBusinessEntity(businessEntity);
    }

    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws SelectException, CreateConnectionException{
        return searchDataManager.getAllProductSoldInADelay(startingDate, endingDate, productType);
    }

    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectException, CreateConnectionException{
        return searchDataManager.getAllProductSupplementDueToEvent(startingDate, endingDate);
    }

    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws SelectException, CreateConnectionException{
        return searchDataManager.getAllAdressesOfABusinessEntity(businessEntity);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws SelectException, CreateConnectionException{
        return searchDataManager.getAllProductOutOfMinimumStock(productType);
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock () throws SelectException, CreateConnectionException{
        return searchDataManager.getAllProductOutOfMinimumStock();
    }

    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectException, CreateConnectionException{
        return searchDataManager.getAllSupplierForAProduct(product, maxDelayDelivery, maxPrice);
    }

}
