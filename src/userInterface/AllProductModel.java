package userInterface;

import model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllProductModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Product> contents;

    public AllProductModel(ArrayList<Product> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("nom");
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
