package userInterface;

import model.BusinessEntityAdress;
import model.SupplierForAProduct;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class AllBusinessesEntityAdressModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<BusinessEntityAdress> contents;

    public AllBusinessesEntityAdressModel(ArrayList<BusinessEntityAdress> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("pays");
        columnNames.add("code postal");
        columnNames.add("ville");
        columnNames.add("numéro");
        columnNames.add("rue");
        setContents(contents);
    }

    public void setContents(ArrayList<BusinessEntityAdress> contents) {
        this.contents = contents;
    }

    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SupplierForAProduct product = contents.get(rowIndex);
        switch (columnIndex) {
            case 0 : return product.getReference();
            case 1 : return product.getName();
            case 2 : return product.getReference();
            case 3 : return product.getVat();
            case 4 : return product.getQuantityInStock();
            case 5 : return product.getMinimumQuantityInStock();
            case 6 : return product.isSparkling();
            case 7 : return product.getAlcoholLevel();
            // change a java.util.Date into a LocalDate with the system default TimeZone
            case 8 : return java.util.Date.from(product.getLaunchingDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            case 9 : return product.getPrice();
            case 10 : return product.getDescription();  // can return null
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) { return columnNames.get(columnIndex);}

    public Class getColumnClass (int columnIndex) {
        Class columnClass = switch (columnIndex) {
            // case 0, 1, 10 -> String.class;    ==> not necessary because it goes in default
            case  2, 4, 5 -> Integer.class;
            case 3, 7, 9 -> Double.class;
            case 6 -> Boolean.class;
            case 8 -> LocalDate.class;
            default -> String.class;
        };
        return columnClass;
    }

    /**
     * getObject() return the object at the given rowIndex
     * @param rowIndex the indew of the row
     * @return type : Product <br> product at the givent row
     **/

    public BusinessEntityAdress getObject(int rowIndex) {
        return this.contents.get(rowIndex);
    }
}
