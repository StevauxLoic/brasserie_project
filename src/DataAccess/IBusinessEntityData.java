package DataAccess;

import model.Adress;
import model.BusinessEntity;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectExeption;

import java.util.ArrayList;

public interface IBusinessEntityData {
    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectExeption, CreateConnectionException;
    public ArrayList<Adress> getAllAdressesOfBusinessEntity(BusinessEntity businessEntity) throws SelectExeption, CreateConnectionException;
}
