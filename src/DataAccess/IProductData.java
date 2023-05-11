package DataAccess;

import model.*;
import model.Exeptions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductData {
    void createProduct(Product productToCreate) throws CreateExeption;
    Product getOneProduct(String referenceOfTheProduct) throws SelectExeption;
    ArrayList<Product> getAllProducts() throws SelectExeption;
    void updateProduct(Product productToUpdate) throws UpdateExeption;
    void deleteProduct(Product productToDelete) throws DeleteExeption;
    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption;
}
