package DataAccess;

import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectExeption;
import model.Product;
import model.ProductSoldInADelay;
import model.ProductSupplementDueToEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchData implements ISearchData{
    private Connection connection;

    public SearchData() throws CreateConnectionException {
        this.connection = SingletonConnection.getUniqueConnection();
    }


    ////// search the count of one product type in a delay
    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption{
        ArrayList<ProductSoldInADelay> allProductsSoldInADelay = new ArrayList<>();
        String sqlInstruction = "SELECT prod.tag, det.quantity, det.price*det.quantity as cost_price FROM details_line det " +
                "INNER JOIN document doc ON doc.id = det.document_id " +
                "INNER JOIN document_type d_type ON d_type = doc.document_type_id " +
                "INNER JOIN product prod ON prod.id = det.product_id " +
                "INNER JOIN product_type p_type ON product_type.p_type = prod.type_id " +
                "WHERE (doc.creation_date > ? AND doc.creation_date < ? " +
                "AND (d_type = 1 AND product_type.p_type = ?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            statement.setInt(3, productType);
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allProductsSoldInADelay.add(new ProductSoldInADelay(data.getString("tag"), data.getDouble("cost_price"), data.getInt("quantity")));
            }
        }
        catch (SQLException exception){
            String message = "Erreur lors du comtpe du nombre de produit vendu \n" +
                    "du type : " + productType + "\n" +
                    "de la date : " + startingDate.toString() + " a la date " + endingDate.toString();
            throw new SelectExeption(message);
        }
        return allProductsSoldInADelay;
        }

    ////// search of product with a Additional Restocking due to a certain event
    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption{
        ArrayList<ProductSupplementDueToEvent> allProductSupplementDueToEvent = new ArrayList<>();
        String sqlInstruction = "SELECT addi.amount as amount, fest.tag as fest, prod.tag as prod, prod.minimum_quantity_in_stock as qtt, prod.id as pro_id, prod_ty.tag as prod_type FROM additional_restocking addi " +
                "INNER JOIN festivity fest ON addi.festivity_id = fest.id " +
                "INNER JOIN product prod ON addi.product_id = prod.id " +
                "INNER JOIN product_type prod_ty ON prod_ty.id = prod.type_id " +
                "WHERE (fest.date_of_the_event > ? AND fest.date_of_the_event < ?) " +
                "GROUP BY fest.tag";
        try{
            PreparedStatement statement = connection.prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allProductSupplementDueToEvent.add(new ProductSupplementDueToEvent(data.getString("fest"), data.getString("prod_type"), data.getString("prod"),
                        data.getString("pro_id"), data.getInt("qtt"), data.getInt("amount")));
            }
        }
        catch (SQLException exception){
            String message = "Erreur lors de la selection des produit avec un restock supplémentaire lros de certains evenement \n" +
                    "de la date : " + startingDate.toString() + "\n" +
                    "a la date : " + endingDate.toString();
            throw new SelectExeption(message);
        }
        return allProductSupplementDueToEvent;
    }

    /*
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
     */
}
