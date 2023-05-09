package DataAccess;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductData {
    void createProduct(Product productToCreate) throws SQLException;
    Product showOneProduct(String referenceOfTheProduct) throws SQLException;
    ArrayList<Product> showAllProducts() throws SQLException;
    void updateProduct(Product productToUpdate) throws SQLException;
    void deleteProduct(Product productToDelete) throws SQLException;
}
