package userInterface;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllProductSoldInADelayModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ProductSoldInADelay> contents;

    public AllProductSoldInADelayModel(ArrayList<ProductSoldInADelay> contents) {
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
        return null;
    }

    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    // TODO à finir
}