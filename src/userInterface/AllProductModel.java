package userInterface;

import model.Product;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class AllProductModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Product> contents;

    public AllProductModel(ArrayList<Product> products) {
        columnNames = new ArrayList<>();
        columnNames.add("référence");
        columnNames.add("nom");
        columnNames.add("type de référence");
        columnNames.add("TVA");
        columnNames.add("quantité en stock");
        columnNames.add("quantité min. en stock");
        columnNames.add("est pétillant");
        columnNames.add("niveau d'alcool");
        columnNames.add("date de lancement");
        columnNames.add("prix (HTVA)");
        columnNames.add("description");
        setContents(products);
    }

    public void setContents(ArrayList<Product> products) {
        this.contents = products;
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
        Product product = contents.get(rowIndex);
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

    public Product getObject(int rowIndex) {
        return this.contents.get(rowIndex);
    }
}
