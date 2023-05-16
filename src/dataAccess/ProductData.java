package dataAccess;

import model.*;
import model.Exeptions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class ProductData implements  IProductData{

    public ProductData() {

    }

    public void createProduct(Product productToCreate) throws CreateDatasException, CreateConnectionException {
        String sql = "INSERT INTO product (id, type_id, tag, vat, quantity_in_stock, minimum_quantity_in_stock, is_sparkling, " +
                "alcohol_level, launching_date, price, description_of_the_product) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sql);
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
            if (productToCreate.getDescription() != null) {
                statement.setString(11, productToCreate.getDescription());
            } else {
                statement.setNull(11, Types.VARCHAR);
            }
            statement.executeUpdate();
        } catch (SQLException exception){
            throw new CreateDatasException("Erreur lors de la création d'un produit.");
        }

    }

    public ArrayList<Product> getAllProducts()throws GetDatasException, CreateConnectionException{
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM product";

        try {
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sql);
            ResultSet data = statement.executeQuery();

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
            throw new GetDatasException("Erreur lors de la récupération de tout les produits enregistrés.");
        }

        return products;
    }

    public void updateProduct(Product productToUpdate) throws ModifyDatasException, CreateConnectionException {
        String sql = "UPDATE product set id = ?, type_id = ?, tag = ?, vat = ?, " +
                    "quantity_in_stock = ?, minimum_quantity_in_stock = ?, is_sparkling = ?, " +
                    "alcohol_level = ?, launching_date = ?, price = ?, description_of_the_product = ? " +
                    "WHERE id = ?";
        try {
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sql);
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
            if (productToUpdate.getDescription() != null) {
                statement.setString(11, productToUpdate.getDescription());
            } else {
                statement.setNull(11, Types.VARCHAR);
            }
            statement.setString(12, productToUpdate.getReference());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new ModifyDatasException("Erreur lors de la modification d'un produit.");
        }
    }

    public void deleteProduct(Product productToDelete) throws DeleteDatasException, CreateConnectionException {
        String [] sqlInstructions = new String[]{
                "DELETE FROM details_line WHERE product_id = ?;",
                "DELETE FROM additional_restocking WHERE product_id = ?;",
                "DELETE FROM supplier_product_details WHERE product_ref = ?;",
                "DELETE FROM product WHERE id = ?"
        };
        try{
            for(int i = 0; i < sqlInstructions.length; i ++){
                PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstructions[i]);
                statement.setString(1, productToDelete.getReference());
                statement.executeUpdate();
            }
        } catch (SQLException exception){
            throw new DeleteDatasException(exception.getMessage());
        }
    }

    public boolean productIdAlreayExist(String productId) throws GetDatasException, CreateConnectionException{
        String sqlInstruction = "SELECT id FROM product WHERE id = ?;";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setString(1, productId);
            ResultSet data = statement.executeQuery();
            return data.next();
        }
        catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la vérification que la référence n'existe pas déjà.");
        }
    }

    public boolean productHasLinksWithOthersDatas(Product product) throws GetDatasException, CreateConnectionException{
        String sqlInstructions [] = new String[] {
                "SELECT * FROM details_line WHERE details_line.product_id = ?",
                "SELECT * FROM additional_restocking WHERE additional_restocking.product_id = ?",
                "SELECT * FROM supplier_product_details WHERE supplier_product_details.product_ref = ?;"
        };

        try{
            int iSelect = 0;
            while(iSelect < sqlInstructions.length){
                PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstructions[iSelect]);
                statement.setString(1, product.getReference());
                ResultSet data = statement.executeQuery();
                if(data.next()){
                    return true;
                }
                iSelect++;
            }
            return false;
        } catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la vérification des liens d'un produit avec d'autres données.");
        }

    }
}
