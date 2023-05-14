package userInterface;

import model.BusinessEntityAdress;

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
        BusinessEntityAdress businessEntityAdress = contents.get(rowIndex);
        switch (columnIndex) {
            case 0 : return businessEntityAdress.getCountry();
            case 1 : return businessEntityAdress.getZipCode();
            case 2 : return businessEntityAdress.getCity();
            case 3 : return businessEntityAdress.getHouseNumber();
            case 4 : return businessEntityAdress.getHouseNumber();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) { return columnNames.get(columnIndex);}

    public Class getColumnClass(int columnIndex) {
        Class columnClass = switch (columnIndex) {
            // case 0, 1, 10 -> String.class;    ==> not necessary because it goes in default
            case  1, 3 -> Integer.class;
            default -> String.class;
        };
        return columnClass;
    }

}
