package userInterface;

import model.ProductSoldInADelay;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllProductsSoldInADelayModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ProductSoldInADelay> contents;

    public AllProductsSoldInADelayModel(ArrayList<ProductSoldInADelay> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("nom du produit");
        columnNames.add("quantité venude");
        columnNames.add("prix de revient");
        setContents(contents);
    }

    public void setContents(ArrayList<ProductSoldInADelay> contents) {
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
        ProductSoldInADelay productSoldInADelay = contents.get(rowIndex);
        switch (columnIndex) {
            case 0 : return productSoldInADelay.getName();
            case 1 : return productSoldInADelay.getQuantity();
            case 2 : return productSoldInADelay.getCostPrice();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) { return columnNames.get(columnIndex);}

    public Class getColumnClass (int columnIndex) {
        Class columnClass = switch (columnIndex) {
            case  1, 2 -> Integer.class;
            default -> String.class;
        };
        return columnClass;
    }
}