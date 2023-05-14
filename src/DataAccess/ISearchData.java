package DataAccess;

import model.BusinessEntityAdress;
import model.Exeptions.SelectExeption;
import model.Product;
import model.ProductSoldInADelay;
import model.ProductSupplementDueToEvent;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ISearchData {
    ArrayList<ProductSoldInADelay> getAllProductSoldInADelay(LocalDate startingDate, LocalDate endingDate, int productType) throws SelectExeption;
    ArrayList<ProductSupplementDueToEvent> getAllProductSupplementDueToEvent(LocalDate startingDate, LocalDate endingDate) throws SelectExeption;
    ArrayList<BusinessEntityAdress> getAllAdressesOfABusinessEntity(String businessEntityId) throws SelectExeption;
}
