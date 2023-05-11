package userInterface;

import model.ProductSupplementDueToEvent;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllProductSupplementsDueToEventModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ProductSupplementDueToEvent> contents;

    public AllProductSupplementsDueToEventModel(ArrayList<ProductSupplementDueToEvent> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("nom d'évènement");
        columnNames.add("type de produit");
        columnNames.add("nom du produit");
        columnNames.add("reférence du produit");
        columnNames.add("minimum nécéssaire en stock");
        columnNames.add("quantité supplémentaire");
        setContents(contents);
    }

    public void setContents(ArrayList<ProductSupplementDueToEvent> contents) {
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

    public ProductSupplementDueToEvent getObject(int rowIndex) {
        return this.contents.get(rowIndex);
    }
}