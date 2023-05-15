package dataAccess;

import model.*;
import model.Exeptions.*;

import java.util.ArrayList;

public interface IProductData {
    void createProduct(Product productToCreate) throws CreateException, CreateConnectionException;
    Product getOneProduct(String referenceOfTheProduct) throws SelectException,CreateConnectionException;
    ArrayList<Product> getAllProducts() throws SelectException, CreateConnectionException;
    void updateProduct(Product productToUpdate) throws UpdateException, CreateConnectionException;
    void deleteProduct(Product productToDelete) throws DeleteException,CreateConnectionException;
}
