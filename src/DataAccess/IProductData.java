package DataAccess;

import model.*;
import model.Exeptions.CreateExeption;
import model.Exeptions.DeleteExeption;
import model.Exeptions.SelectExeption;
import model.Exeptions.UpdateExeption;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductData {
    void createProduct(Product productToCreate) throws CreateExeption;
    Product showOneProduct(String referenceOfTheProduct) throws SelectExeption;
    ArrayList<Product> showAllProducts() throws SelectExeption;
    void updateProduct(Product productToUpdate) throws UpdateExeption;
    void deleteProduct(Product productToDelete) throws DeleteExeption;
}
