package DataAccess;

import model.Adress;
import model.BusinessEntity;
import model.Exeptions.SelectExeption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BusinessEntityData implements IBusinessEntityData{
    private Connection connection;

    public BusinessEntityData(){
        connection = SingletonConnection.getUniqueConnection();
    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectExeption {
        ArrayList<BusinessEntity> businessEntities = new ArrayList<>();
        BusinessEntity businessEntity;
        String sql = "SELECT * FROM business_entity";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                businessEntity = new BusinessEntity(data.getString("id"), data.getString("denomination"), data.getLong("vat_number"), data.getString("statut_id"));
                businessEntities.add(businessEntity);
            }
        }catch (SQLException exception){
            String message = "Erreur lors de la récupération de la liste des business entity";
            throw new SelectExeption(message);
        }
        return businessEntities;
    }

    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectExeption{
        ArrayList<Adress> adresses = new ArrayList<>();
        Adress adress;
        String sql = "SELECT * FROM adress WHERE id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, businessEntity.getReference());
            ResultSet data = statement.executeQuery();

            while(data.next()){
                adress = new Adress(data.getString("id"), data.getString("street"), data.getInt("number_of_the_hours"), data.getString("business_entity_id"), data.getInt("type_id"), data.getString("city_id"));
            }
        }catch (SQLException exception){
            String message = "Erreur lors de la récupération de la liste des adresse pour la business entity" + businessEntity.getName();
            throw new SelectExeption(message);
        }
        return adresses;
    }
}
