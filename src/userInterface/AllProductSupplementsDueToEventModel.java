package userInterface;

import model.ProductSupplementDueToEvent;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
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
        ProductSupplementDueToEvent productSupplementDueToEvent = contents.get(rowIndex);
        switch (columnIndex) {
            case 0 : return productSupplementDueToEvent.getEventName();
            case 1 : return productSupplementDueToEvent.getProductTypeName();
            case 2 : return productSupplementDueToEvent.getProductName();
            case 3 : return productSupplementDueToEvent.getProductReference();
            case 4 : return productSupplementDueToEvent.getMinimumQuantityInStock();
            case 5 : return productSupplementDueToEvent.getAmount();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) { return columnNames.get(columnIndex);}

    public Class getColumnClass (int columnIndex) {
        Class columnClass = switch (columnIndex) {
            case  4, 5 -> Integer.class;
            default -> String.class;
        };
        return columnClass;
    }
}