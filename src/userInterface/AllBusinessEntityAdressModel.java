package userInterface;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllBusinessEntityAdressModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<BusinessEntityAdress> contents;

    public AllBusinessEntityAdressModel(ArrayList<BusinessEntityAdress> contents) {
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

    // TODO à finir

    /**
     * getObject() return the object at the given rowIndex
     * @param rowIndex the indew of the row
     * @return type : Product <br> product at the givent row
     **/
    public BusinessEntityAdress getObject(int rowIndex) {
        return this.contents.get(rowIndex);
    }
}