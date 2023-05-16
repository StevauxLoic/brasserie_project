package dataAccess;

import model.BusinessEntity;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BusinessEntityData implements IBusinessEntityData{

    public BusinessEntityData() {

    }

    public ArrayList<BusinessEntity> getAllBusinessEntities() throws GetDatasException, CreateConnectionException {
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
            throw new GetDatasException("Erreur lors de la récupération des données sur les personnes/entreprises enregistrées.");
        }
        return businessEntities;
    }


}
