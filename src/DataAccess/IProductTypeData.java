package DataAccess;

import model.Exeptions.CreateConnectionException;
import model.ProductType;

import java.util.ArrayList;

public interface IProductTypeData {
    ArrayList<ProductType> getAllProductType() throws ProductTypeException, CreateConnectionException;
}
