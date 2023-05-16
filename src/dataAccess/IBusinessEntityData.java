package dataAccess;

import model.BusinessEntity;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;

import java.util.ArrayList;

public interface IBusinessEntityData {
    public ArrayList<BusinessEntity> getAllBusinessEntities() throws GetDatasException, CreateConnectionException;

}
