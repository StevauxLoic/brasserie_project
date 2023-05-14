package Business;

import DataAccess.BusinessEntityData;
import DataAccess.ProductData;
import DataAccess.SearchData;
import DataAccess.SingletonConnection;
import model.*;
import model.Exeptions.*;

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


    public void closeConnection() throws CloseConnectionException {
        try {
            SingletonConnection.getUniqueConnection().close();
        } catch (SQLException event) {
            throw new CloseConnectionException("la fermeture de la connexion d'accès aux données");
        } catch (CreateConnectionException event) {

        }
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

}
