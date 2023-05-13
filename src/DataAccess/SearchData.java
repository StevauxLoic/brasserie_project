package DataAccess;

import model.Exeptions.SelectExeption;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchData implements ISearchData{
    private Connection connection;

    public SearchData(){
        this.connection = SingletonConnection.getUniqueConnection();
    }

    ////// search the count of one product type in a delay
    public int countQuantitySoldOfProduct(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption {
        String sqlInstruction = "SELECT SUM(quantity) as count_product_sold FROM details_line det " +
                "INNER JOIN document doc ON doc.id = det.document_id " +
                "INNER JOIN document_type d_type ON d_type = doc.document_type_id " +
                "INNER JOIN product prod ON prod.id = det.product_id " +
                "INNER JOIN product_type p_type ON product_type.p_type = prod.type_id " +
                "WHERE (doc.creation_date > ? AND doc.creation_date < ? " +
                "AND (d_type = 1 AND product_type.p_type = ?)";
        int count;
        try{
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            statement.setInt(3, productType);
            ResultSet data = statement.executeQuery();
            count = data.getInt("count_product_sold");
        } catch (SQLException exeption){
            String message = "Erreur lors du comtpe du nombre de produit vendu \n" +
                    "du type : " + productType + "\n" +
                    "de la date : " + startingDate.toString() + " a la date " + endingDate.toString();
            throw new SelectExeption(message);
        }
        return count;
    }

    ////// search of product with a Additional Restocking due to a certain event
    public ArrayList<Product> getProductsWithAdditionalRestockingInADelay(LocalDate startingDate, LocalDate endingDate) throws SelectExeption{
        ArrayList<Product> productsWithAdditionalRestockingInADelay = new ArrayList<>();
        Product product;
        String sqlInstruction = "";

        try{
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                product = new Product(data.getString("id"), data.getInt("type_id"), data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                        data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(), data.getDouble("price"), data.getDouble("alcohol_level"),
                        data.getInt("quantity_in_stock")) ;
                String description = data.getString("description_of_the_product");
                if(!data.wasNull()){
                    product.setDescription(description);
                }

                productsWithAdditionalRestockingInADelay.add(product);
            }
        }
        catch (SQLException exception){
            String message = "Erreur lors de la selection des produit avec un restock supplémentaire lros de certains evenement \n" +
                    "de la date : " + startingDate.toString() + "\n" +
                    "a la date : " + endingDate.toString();
            throw new SelectExeption(message);
        }
        return productsWithAdditionalRestockingInADelay;
    }
}
