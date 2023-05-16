package dataAccess;

import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import model.ProductType;

import java.util.ArrayList;

public interface IProductTypeData {
    ArrayList<ProductType> getAllProductType() throws GetDatasException, CreateConnectionException;
}
