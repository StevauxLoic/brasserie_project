package dataAccess;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import model.Exeptions.SupplierForAProductException;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchData implements ISearchData{

    public SearchData(){

    }


    ////// search the count of one product type in a delay
    public ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, ProductType productType) throws GetDatasException, CreateConnectionException{
        ArrayList<ProductSoldInADelay> allProductsSoldInADelay = new ArrayList<>();
        String sqlInstruction = "SELECT prod.tag, SUM(det.quantity) AS quantities, SUM(det.price * det.quantity) AS cost_price FROM details_line det " +
                                "INNER JOIN document doc ON doc.id = det.document_id " +
                                "INNER JOIN document_type d_type ON d_type.id = doc.document_type_id " +
                                "INNER JOIN product prod ON prod.id = det.product_id " +
                                "INNER JOIN product_type p_type ON p_type.id = prod.type_id " +
                                "WHERE doc.creation_date >= ? AND doc.creation_date <= ? " +
                                "AND d_type.id = 1 AND p_type.id = ? " +
                                "GROUP BY prod.tag;";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            statement.setInt(3, productType.getReference());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allProductsSoldInADelay.add(new ProductSoldInADelay(data.getString("tag"),
                        data.getDouble("cost_price"), data.getInt("quantities")));
            }
        }
        catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la récupération des produits vendu dans un interval " +
                    "de la date : " + startingDate.toString() + " à la date " + endingDate.toString() + '.');
        }
        return allProductsSoldInADelay;
        }

    ////// search of product with a Additional Restocking due to a certain event
    public ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws GetDatasException, CreateConnectionException{
        ArrayList<ProductSupplementDueToEvent> allProductSupplementDueToEvent = new ArrayList<>();
        String sqlInstruction = "SELECT addi.amount AS amount, fest.tag AS fest, prod.tag AS prod, prod.minimum_quantity_in_stock AS " +
                                "qtt, prod.id AS pro_id, prod_ty.tag AS prod_type FROM additional_restocking addi " +
                                "INNER JOIN festivity fest ON addi.festivity_id = fest.id " +
                                "INNER JOIN product prod ON addi.product_id = prod.id " +
                                "INNER JOIN product_type prod_ty ON prod_ty.id = prod.type_id " +
                                "WHERE (fest.date_of_the_event > ? AND fest.date_of_the_event < ?) " +
                                "ORDER BY fest.tag, prod_ty.id;";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setDate(1, java.sql.Date.valueOf(startingDate));
            statement.setDate(2, java.sql.Date.valueOf(endingDate));
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allProductSupplementDueToEvent.add(new ProductSupplementDueToEvent(data.getString("fest"),
                        data.getString("prod_type"), data.getString("prod"),
                        data.getString("pro_id"), data.getInt("qtt"), data.getInt("amount")));
            }
        }
        catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la récupération des besoins de stocks supplémentaire dûts aux " +
                    "evements se déroulant de la date : " + startingDate.toString() + " à la date : " + endingDate.toString() + '.');
        }
        return allProductSupplementDueToEvent;
    }


    public ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(BusinessEntity businessEntity) throws GetDatasException, CreateConnectionException{
        ArrayList<BusinessEntityAdress> allAdressesOfABusinessEntity = new ArrayList<>();
        String sqlInstruction = "SELECT coun.tag AS country_name, cit.zip_code AS zip_code, " +
                                "cit.tag AS city_name, adre.street AS adress_street, " +
                                "adre.number_of_the_house AS adress_number, adre_ty.tag AS adress_type_name " +
                                "FROM adress adre " +
                                "INNER JOIN business_entity busi ON adre.business_entity_id = busi.id " +
                                "INNER JOIN adress_type adre_ty ON adre.type_id = adre_ty.id " +
                                "INNER JOIN city cit ON adre.city_id = cit.id " +
                                "INNER JOIN country coun ON cit.country_id = coun.id " +
                                "WHERE busi.id = ? " +
                                "ORDER BY coun.tag, cit.tag;";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setString(1, businessEntity.getReference());
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                allAdressesOfABusinessEntity.add(new BusinessEntityAdress(data.getString("country_name"),
                        data.getString("city_name"), data.getString("adress_street"),
                        data.getInt("zip_code"), data.getInt("adress_number"), data.getString("adress_type_name")));
            }
        }
        catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la récupération des adresse d'une personne/entrerpsie données.");
        }
        return allAdressesOfABusinessEntity;
    }

    ////// here are the methods to help the employee to see wich supplier is better for a particular product when it's out of minimum stock
    /// first methods that return an array of pruduct out of minimum stocks depends o the type or not
    public ArrayList<Product> getAllProductOutOfMinimumStock (ProductType productType) throws GetDatasException, CreateConnectionException{
        ArrayList<Product> allProductOutOfMinimuStock = new ArrayList<>();
        Product product;
        String sqlInstruction;
        if(productType != null){
            sqlInstruction = "SELECT * FROM product " +
                            "WHERE quantity_in_stock < minimum_quantity_in_stock " +
                            "AND product.type_id = ?;";
        } else {
            sqlInstruction = "SELECT * FROM product " +
                            "WHERE quantity_in_stock < minimum_quantity_in_stock;";
        }
        try {
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            if(productType != null) {
                statement.setInt(1, productType.getReference());

            }
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                product = new Product(data.getString("id"), data.getInt("type_id"),
                        data.getString("tag"), data.getDouble("vat"), data.getInt("minimum_quantity_in_stock"),
                        data.getBoolean("is_sparkling"), data.getDate("launching_date").toLocalDate(),
                        data.getDouble("price"), data.getDouble("alcohol_level"),
                        data.getInt("quantity_in_stock"));

                String description = data.getString("description_of_the_product");
                if(!data.wasNull()){
                    product.setDescription(description);
                }
                allProductOutOfMinimuStock.add(product);
            }
        }
        catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la recherche des produits en rupture de stock.");
        }
        return allProductOutOfMinimuStock;
    }

    public ArrayList<Product> getAllProductOutOfMinimumStock () throws GetDatasException, CreateConnectionException {
        return getAllProductOutOfMinimumStock(null);
    }

    /// this methods return a list of supplier that respect the conditions proposed by the form
    public ArrayList<SupplierForAProduct> getAllSupplierForAProduct(Product product, Integer maxDelayDelivery, Double maxPrice) throws GetDatasException, CreateConnectionException {
        ArrayList<SupplierForAProduct> allSupplierForAProduct = new ArrayList<>();
        String sqlInstruction = "SELECT busi.denomination AS busi_name, busi.id AS busi_id, supp.price AS price, supp.delivery_time AS delivery_time, statut.denomination AS statut_name FROM supplier_product_details supp " +
                                "INNER JOIN business_entity busi ON supp.business_entity_ref = busi.id " +
                                "INNER JOIN statut ON statut.id = busi.statut_id " +
                                "WHERE supp.product_ref = ? ";
        if (maxDelayDelivery != null) {
            sqlInstruction += " AND supp.delivery_time <= ?";
        }
        if (maxPrice != null) {
            sqlInstruction += " AND supp.price <= ?";
        }
        sqlInstruction += ';';

        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sqlInstruction);
            statement.setString(1, product.getReference());

            if (maxDelayDelivery != null) {
                statement.setInt(2, maxDelayDelivery);
                if (maxPrice != null) {
                    statement.setDouble(3, maxPrice);
                }
            } else if (maxPrice != null) {
                statement.setDouble(2, maxPrice);
            }

            ResultSet data = statement.executeQuery();

            while (data.next()) {
                allSupplierForAProduct.add(new SupplierForAProduct(data.getString("busi_name"), data.getString("busi_id"),
                        data.getString("statut_name"), data.getDouble("price"), data.getInt("delivery_time")));
            }
        }
        catch (SQLException exception){
            throw new GetDatasException("Erreur lors de la récupération des fournisseurs pour un produit donné.");
        } catch (SupplierForAProductException exception) {
            throw new GetDatasException("Erreur lors de la manipulation des données (données sur les fournisseurs qui vnedent un produit donné).");
        } catch (CreateConnectionException e) {
            throw new CreateConnectionException("Erreur lors de la création de la connexion aux données");
        }
        return allSupplierForAProduct;
    }
}
