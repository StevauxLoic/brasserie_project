package dataAccess;

import model.*;
import model.Exeptions.*;

import java.util.ArrayList;

public interface IProductData {
    void createProduct(Product productToCreate) throws CreateDatasException, CreateConnectionException;
    ArrayList<Product> getAllProducts() throws GetDatasException, CreateConnectionException;
    void updateProduct(Product productToUpdate) throws ModifyDatasException, CreateConnectionException;
    void deleteProduct(Product productToDelete) throws DeleteDatasException,CreateConnectionException;
    boolean productHasLinksWithOthersDatas(Product product) throws GetDatasException, CreateConnectionException; // return true if links exist
    boolean productIdAlreayExist(Product productToVerify) throws GetDatasException, CreateConnectionException; // return true if id already exist
}
