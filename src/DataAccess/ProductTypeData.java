package DataAccess;

import model.Exeptions.CreateConnectionException;
import model.Exeptions.ProductTypeExeption;
import model.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTypeData {

    public ProductTypeData(){

    }

    public ArrayList<ProductType> getAllProductType() throws ProductTypeExeption, CreateConnectionException {
        ArrayList<ProductType> productTypes = new ArrayList<>();
        ProductType productType;
        String sql = "SELECT * FROM product_type" +
                "WHERE id = ?";
        try {
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                productType = new ProductType(data.getInt("id"), data.getString("tag"));
                productTypes.add(productType);
            }

        }catch (SQLException exception) {
            throw new ProductTypeExeption(exception.getMessage());
        }

        return productTypes;
    }
}
