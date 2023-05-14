package DataAccess;

import model.*;
import model.Exeptions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductData {
    void createProduct(Product productToCreate) throws CreateExeption, CreateConnectionException;
    Product getOneProduct(String referenceOfTheProduct) throws SelectExeption,CreateConnectionException;
    ArrayList<Product> getAllProducts() throws SelectExeption, CreateConnectionException;
    void updateProduct(Product productToUpdate) throws UpdateExeption, CreateConnectionException;
    void deleteProduct(Product productToDelete) throws DeleteExeption,CreateConnectionException;
}
