package DataAccess;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductData implements  IProductData{
    private Connection connection = SingletonConnection.getUniqueConnection();

    public void createProduct(Product productToCreate) throws SQLException {
        String sql = "INSERT INTO product (id, type_id, tag, vat, quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productToCreate.getReference());
        statement.setInt(2, productToCreate.getTypeReference());
        statement.setString(3, productToCreate.getName());
        statement.setDouble(4, productToCreate.getVat());
        statement.setInt(5, productToCreate.getQuantityInStock());
        statement.setBoolean(6, productToCreate.isSparkling());
        statement.setDouble(7, productToCreate.getAlcoholLevel());
        statement.setDate(8, java.sql.Date.valueOf(productToCreate.getLaunchingDate()));
        statement.setDouble(9, productToCreate.getPrice());
        statement.setString(10, productToCreate.getDescription());
        statement.executeUpdate();
    }


    public Product showOneProduct(String referenceOfTheProduct) throws SQLException{
        String sql = "SELECT id, type_id, tag, vat, quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product" +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, referenceOfTheProduct);
        statement.executeQuery();
    }

    public ArrayList<Product> showAllProducts()throws  SQLException{

    }

    public void updateProduct(Product productToUpdate) throws SQLException{
        String sql = "UPDATE product set (id = ?, type_id = ?, tag = ?, vat = ?, quantity_in_stock = ?, is_sparkling = ?, alcohol_level = ?, launching_date = ?, price = ?, description_of_the_product = ?) " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productToUpdate.getReference());
        statement.setInt(2, productToUpdate.getTypeReference());
        statement.setString(3, productToUpdate.getName());
        statement.setDouble(4, productToUpdate.getVat());
        statement.setInt(5, productToUpdate.getQuantityInStock());
        statement.setBoolean(6, productToUpdate.isSparkling());
        statement.setDouble(7, productToUpdate.getAlcoholLevel());
        statement.setDate(8, java.sql.Date.valueOf(productToUpdate.getLaunchingDate()));
        statement.setDouble(9, productToUpdate.getPrice());
        statement.setString(10, productToUpdate.getDescription());
        statement.setString(11, productToUpdate.getReference());
        statement.executeUpdate();
    }

    public void deleteProduct(Product productToDelete) throws SQLException{
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productToDelete.getReference());
    }
}
