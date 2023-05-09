package DataAccess;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// TODO qd on crée c'est qu'avec les truc obligatoire

public class ProductData implements  IProductData{
    private Connection connection = SingletonConnection.getUniqueConnection();

    public void createProduct(Product productToCreate) throws SQLException {
        String sql = "INSERT INTO product (id, type_id, tag, vat, quantity_in_stock, minimum_quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productToCreate.getReference());
        statement.setInt(2, productToCreate.getTypeReference());
        statement.setString(3, productToCreate.getName());
        statement.setDouble(4, productToCreate.getVat());
        statement.setInt(5, productToCreate.getQuantityInStock());
        statement.setInt(6, productToCreate.getMinimumQuantityInStock());
        statement.setBoolean(7, productToCreate.isSparkling());
        statement.setDouble(8, productToCreate.getAlcoholLevel());
        statement.setDate(9, java.sql.Date.valueOf(productToCreate.getLaunchingDate()));
        statement.setDouble(10, productToCreate.getPrice());
        statement.setString(11, productToCreate.getDescription());
        statement.executeUpdate();
    }


    public Product showOneProduct(String referenceOfTheProduct) throws SQLException, ReferenceExeption, TypeExeption, NameExeption, DateExeption {
        String sql = "SELECT id, type_id, tag, vat, quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product" +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, referenceOfTheProduct);
        ResultSet data = statement.executeQuery();
        data.next();
        Product product = new Product(data.getString("id"), data.getInt("type_id"), data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(), data.getDouble("price"), data.getDouble("alcohol_level"), data.getString("description_of_the_product"),
                data.getInt("quantity_in_stock")) ;
        return product;
    }

    public ArrayList<Product> showAllProducts()throws SQLException, ReferenceExeption , TypeExeption, NameExeption, DateExeption{
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM Product";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            ResultSet data = statement.executeQuery();
            String VATNumber, siteName;

            while (data.next()) {
                 product = new Product(data.getString("id"), data.getInt("type_id"), data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                        data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(), data.getDouble("price"), data.getDouble("alcohol_level"), data.getString("description_of_the_product"),
                        data.getInt("quantity_in_stock")
                ) ;



                products.add(product);
            }

        }catch (SQLException e) {

        }

        return products;
    }

    public void updateProduct(Product productToUpdate) throws SQLException{
        String sql = "UPDATE product set (id = ?, type_id = ?, tag = ?, vat = ?, quantity_in_stock = ?, minimum_quantity_in_stock = ?, is_sparkling = ?, alcohol_level = ?, launching_date = ?, price = ?, description_of_the_product = ?)" +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productToUpdate.getReference());
        statement.setInt(2, productToUpdate.getTypeReference());
        statement.setString(3, productToUpdate.getName());
        statement.setDouble(4, productToUpdate.getVat());
        statement.setInt(5, productToUpdate.getQuantityInStock());
        statement.setInt(6, productToUpdate.getMinimumQuantityInStock());
        statement.setBoolean(7, productToUpdate.isSparkling());
        statement.setDouble(8, productToUpdate.getAlcoholLevel());
        statement.setDate(9, java.sql.Date.valueOf(productToUpdate.getLaunchingDate()));
        statement.setDouble(10, productToUpdate.getPrice());
        statement.setString(11, productToUpdate.getDescription());
        statement.setString(12, productToUpdate.getReference());
        statement.executeUpdate();
    }

    public void deleteProduct(Product productToDelete) throws SQLException{
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productToDelete.getReference());
    }
}