package dataAccess;

import model.BusinessEntity;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;

import java.util.ArrayList;

public interface IBusinessEntityData {
    public ArrayList<BusinessEntity> getAllBusinessEntities() throws SelectException, CreateConnectionException;

}
