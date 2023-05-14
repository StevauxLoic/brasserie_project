package DataAccess;

import model.Adress;
import model.BusinessEntity;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BusinessEntityData implements IBusinessEntityData{

    public BusinessEntityData() {

    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectException, CreateConnectionException {
        ArrayList<BusinessEntity> businessEntities = new ArrayList<>();
        BusinessEntity businessEntity;
        String sql = "SELECT * FROM business_entity";
        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                businessEntity = new BusinessEntity(data.getString("id"), data.getString("denomination"), data.getLong("vat_number"), data.getString("statut_id"));
                businessEntities.add(businessEntity);
            }
        }catch (SQLException exception){
            throw new SelectException(exception.getMessage(), "la liste des business entity");
        }
        return businessEntities;
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectException, CreateConnectionException {
        ArrayList<Adress> adresses = new ArrayList<>();
        Adress adress;
        String sql = "SELECT * FROM adress WHERE id = ?";

        try{
            PreparedStatement statement = SingletonConnection.getUniqueConnection().prepareStatement(sql);
            statement.setString(1, businessEntity.getReference());
            ResultSet data = statement.executeQuery();

            while(data.next()){
                adress = new Adress(data.getString("id"), data.getString("street"), data.getInt("number_of_the_hours"), data.getString("business_entity_id"), data.getInt("type_id"), data.getString("city_id"));
            }
        }catch (SQLException exception){
            throw new SelectException(exception.getMessage(), "la liste des adresse pour la business entity" + businessEntity.getName());
        }
        return adresses;
    }
}
