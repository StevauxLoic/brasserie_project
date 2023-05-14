package DataAccess;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectExeption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchData implements ISearchData{

    public SearchData(){

    }


    ////// search the count of one product type in a delay
    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption, CreateConnectionException{
        ArrayList<ProductSoldInADelay> allProductsSoldInADelay = new ArrayList<>();
        String sqlInstruction = "SELECT prod.tag, det.quantity, det.price*det.quantity as cost_price FROM details_line det " +
                "INNER JOIN document doc ON doc.id = det.document_id " +
                "INNER JOIN document_type d_type ON d_type = doc.document_type_id " +
                "INNER JOIN product prod ON prod.id = det.product_id " +
                "INNER JOIN product_type p_type ON product_type.p_type = prod.type_id " +
                "WHERE (doc.creation_date > ? AND doc.creation_date < ? " +
                "AND (d_type = 1 AND product_type.p_type = ?)";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            statement.setInt(3, productType);
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allProductsSoldInADelay.add(new ProductSoldInADelay(data.getString("tag"), data.getDouble("cost_price"), data.getInt("quantity")));
            }
        }
        catch (SQLException exception){
            throw new SelectExeption(exception.getMessage(), "des info d'un produit vendu \n" + "de la date : " + startingDate.toString() + " a la date " + endingDate.toString());
        }
        return allProductsSoldInADelay;
        }

    ////// search of product with a Additional Restocking due to a certain event
    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption, CreateConnectionException{
        ArrayList<ProductSupplementDueToEvent> allProductSupplementDueToEvent = new ArrayList<>();
        String sqlInstruction = "SELECT addi.amount as amount, fest.tag as fest, prod.tag as prod, prod.minimum_quantity_in_stock as qtt, prod.id as pro_id, prod_ty.tag as prod_type FROM additional_restocking addi " +
                "INNER JOIN festivity fest ON addi.festivity_id = fest.id " +
                "INNER JOIN product prod ON addi.product_id = prod.id " +
                "INNER JOIN product_type prod_ty ON prod_ty.id = prod.type_id " +
                "WHERE (fest.date_of_the_event > ? AND fest.date_of_the_event < ?) " +
                "ORDER BY fest.tag, prod_ty.id";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allProductSupplementDueToEvent.add(new ProductSupplementDueToEvent(data.getString("fest"), data.getString("prod_type"), data.getString("prod"),
                        data.getString("pro_id"), data.getInt("qtt"), data.getInt("amount")));
            }
        }
        catch (SQLException exception){
            throw new SelectExeption(exception.getMessage(), "de la liste des restock supplémentaire lros de certains evement\n" + "de la date : " + startingDate.toString() + "\n" +
                    "a la date : " + endingDate.toString());
        }
        return allProductSupplementDueToEvent;
    }

    //////
    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption, CreateConnectionException{
        ArrayList<BusinessEntityAdress> allAdressesOfABusinessEntity = new ArrayList<>();
        String sqlInstruction = "SELECT coun.tag as country_name, cit.zip_code as zip_code, cit.tag as city_name, adre.street as adress_street, adre.number_of_the_house as adress_number FROM adress adre " +
                "INNER JOIN business_entity busi ON adre.business_entity_id = busi.id " +
                "INNER JOIN adress_type adre_ty ON adre.type_id = adre_ty.id " +
                "INNER JOIN city cit ON adre.city_id = cit.id " +
                "INNER JOIN country coun ON cit.country_id = coun.id " +
                "WHERE busi.id = ? " +
                "ORDER BY coun.tag, city.tag";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setString(1, businessEntityId);
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allAdressesOfABusinessEntity.add(new BusinessEntityAdress(data.getString("country_name"), data.getString("city_name"), data.getString("adress_street"),
                        data.getInt("zip_code"), data.getInt("adress_number")));
            }
        }
        catch (SQLException exception){
            throw new SelectExeption(exception.getMessage(), "de la liste des adresse de l'entité business");
        }
        return allAdressesOfABusinessEntity;
    }

    ////// here are the methods to help the employee to see wich supplier is better for a particular product when it's out of minimum stock
    /// first methods that return an array of pruduct out of minimum stocks depends o the type or not
    public ArrayList<Product> getAllProductOutOfMinimumStock (Integer productType) throws SelectExeption, CreateConnectionException{
        ArrayList<Product> allProductOutOfMinimuStock = new ArrayList<>();
        Product product;
        String sqlInstruction;
        if(productType != null){
            sqlInstruction = "SELECT * FROM product\n" +
                    "WHERE quantity_in_stock < minimum_quantity_in_stock product.type_id = ?";
        } else {
            sqlInstruction = "SELECT * FROM product\n" +
                    "WHERE quantity_in_stock < minimum_quantity_in_stock";
        }

        try {
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            if(productType != null) {

                statement.setInt(1, productType);
            }
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                product = new Product(data.getString("id"), data.getInt("type_id"), data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                        data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(), data.getDouble("price"), data.getDouble("alcohol_level"),
                        data.getInt("quantity_in_stock"));
                String description = data.getString("description_of_the_product");
                if(!data.wasNull()){
                    product.setDescription(description);
                }
                allProductOutOfMinimuStock.add(product);
            }
        }
        catch (SQLException exception){
            throw new SelectExeption(exception.getMessage(), "des produit ayant une quantité inférieur a la quantité minmum a avoir ");
        }
        return allProductOutOfMinimuStock;
    }

    /// this methods return a list of supplier that respect the conditions proposed by the form
    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws SelectExeption, CreateConnectionException{
        ArrayList<SupplierForAProduct> allSupplierForAProduct = new ArrayList<>();
        String sqlInstruction = "SELECT busi.denomination as busi_name, busi.id as busi_id, supp.price as price, supp.delivery_time as delivery_time, statut.denomination as statut_name FROM supplier_product_details supp " +
                "INNER JOIN business_entity busi ON supp.business_entity_ref = busi.id " +
                "INNER JOIN statut ON statut.id = busi.statut_id ";
        if(maxDelayDelivery != null && maxPrice != null){
            sqlInstruction += "WHERE supp.product_ref = ? AND supp.delivery_time <= ? AND supp.price <= ?";
        } else {
            if (maxDelayDelivery != null && maxPrice == null){
                sqlInstruction += "WHERE supp.product_ref = ? AND supp.delivery_time <= ?";
            } else {
                if(maxDelayDelivery == null && maxPrice != null){
                    sqlInstruction += "WHERE supp.product_ref = ? AND supp.price <= ?";
                }
            }
        }
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            if(maxDelayDelivery != null && maxPrice != null){
                statement.setInt(1, maxDelayDelivery);
                statement.setDouble(2, maxPrice);
            } else {
                if(maxDelayDelivery != null && maxPrice == null){
                    statement.setInt(1, maxDelayDelivery);
                } else {
                    if(maxDelayDelivery == null && maxPrice!= null){
                        statement.setDouble(1, maxPrice);
                    }
                }
            }
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allSupplierForAProduct.add(new SupplierForAProduct(data.getString("busi_name"), data.getString("busi_id"),
                        data.getString("statut_name"), data.getDouble("price"), data.getInt("delivery_time")));
            }
        }
        catch (SQLException exception){
            throw new SelectExeption(exception.getMessage(), "des fournisseur pour le produit : " + product.getName());
        }
        return allSupplierForAProduct;
    }
}
