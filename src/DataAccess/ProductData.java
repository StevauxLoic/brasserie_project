package DataAccess;

import model.*;
import model.Exeptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// TODO qd on crée c'est qu'avec les truc obligatoire

public class ProductData implements  IProductData{
    private Connection connection;

    public ProductData(){
        this.connection = SingletonConnection.getUniqueConnection();
    }

    public void createProduct(Product productToCreate) throws CreateExeption {
        String sql = "INSERT INTO product (id, type_id, tag, vat, quantity_in_stock, minimum_quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try{
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
            if(productToCreate.getDescription() != null) {
                statement.setString(11, productToCreate.getDescription());
            }
            statement.executeUpdate();
        } catch (SQLException exception){
            String message = "Erreur lors de la crétaion du produit";
            throw new CreateExeption(message);
        }

    }


    public Product getOneProduct(String referenceOfTheProduct) throws SelectExeption {
        String sql = "SELECT id, type_id, tag, vat, quantity_in_stock, is_sparkling, alcohol_level, launching_date, price, description_of_the_product" +
                "WHERE id = ?";
        Product product;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, referenceOfTheProduct);
            ResultSet data = statement.executeQuery();
            data.next();
            product = new Product(data.getString("id"), data.getInt("type_id"), data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                    data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(), data.getDouble("price"), data.getDouble("alcohol_level"),
                    data.getInt("quantity_in_stock")) ;
            String description = data.getString("description_of_the_product");
            if(!data.wasNull()){
                product.setDescription(description);
            }
        } catch (SQLException exception){
            String message = "Erreur lors de la récupération du produit demandé" + referenceOfTheProduct;
            throw new SelectExeption(message);
        }

        return product;
    }

    public ArrayList<Product> getAllProducts()throws SelectExeption{
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM Product";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            String VATNumber, siteName;

            while (data.next()) {
                product = new Product(data.getString("id"), data.getInt("type_id"), data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                        data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(), data.getDouble("price"), data.getDouble("alcohol_level"),
                        data.getInt("quantity_in_stock")) ;
                String description = data.getString("description_of_the_product");
                if(!data.wasNull()){
                    product.setDescription(description);
                }

                products.add(product);
            }

        }catch (SQLException exception) {
            String message = "Erreur lors de la récupération de la liste de produits";
            throw new SelectExeption(message);
        }

        return products;
    }

    public void updateProduct(Product productToUpdate) throws UpdateExeption {
        String sql = "UPDATE product set (id = ?, type_id = ?, tag = ?, vat = ?, quantity_in_stock = ?, minimum_quantity_in_stock = ?, is_sparkling = ?, alcohol_level = ?, launching_date = ?, price = ?, description_of_the_product = ?)" +
                "WHERE id = ?";
        try {
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
        } catch (SQLException exception) {
            String message = "Erreur lors de la supprésion du produit d'id = " + productToUpdate.getReference();
            throw new UpdateExeption(message);
        }
    }

    public void deleteProduct(Product productToDelete) throws DeleteExeption {
        String [] sqlInstructions = new String[]{
                "DELETE FROM details_line WHERE product_id = ?;",
                "DELETE FROM additional_restocking WHERE product_id = ?;",
                "DELETE FROM details_line WHERE product_id = ?;"
        };
        try{
            for(int i = 0; i < 3; i ++){
                PreparedStatement statement = connection.prepareStatement(sqlInstructions[i]);
                statement.setString(1, productToDelete.getReference());
                statement.executeUpdate();
            }
        } catch (SQLException exception){
            String message = "Erreur lors de la suppresion du produit d'id = " + productToDelete.getReference();
            throw new DeleteExeption(message);
        }
    }

    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption {
        ArrayList<ProductType> productTypes = new ArrayList<>();
        ProductType productType;
        String sql = "SELECT * FROM product_type" +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                productType = new ProductType(data.getInt("id"), data.getString("tag"));
                productTypes.add(productType);
            }

        }catch (SQLException exception) {
            String message = "Erreur lors de la récupération de la liste des types de produits";
            throw new ProductTypeExeption(message);
        }

        return productTypes;
    }
}
