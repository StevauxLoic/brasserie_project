package dataAccess;

import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;
import model.ProductType;

import java.util.ArrayList;

public interface IProductTypeData {
    ArrayList<ProductType> getAllProductType() throws SelectException, CreateConnectionException;
}
