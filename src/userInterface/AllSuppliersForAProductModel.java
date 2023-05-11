package userInterface;

import model.SupplierForAProduct;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllSuppliersForAProductModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SupplierForAProduct> contents;

    public AllSuppliersForAProductModel(ArrayList<SupplierForAProduct> contents) {
        columnNames = new ArrayList<>();
        columnNames.add("fournisseur");
        columnNames.add("référence du fournisseur");
        columnNames.add("prix du produit");
        columnNames.add("délai de livraison");
        columnNames.add("statut du fournisseur");
        setContents(contents);
    }

    public void setContents(ArrayList<SupplierForAProduct> contents) {
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

    public SupplierForAProduct getObject(int rowIndex) {
        return this.contents.get(rowIndex);
    }
}

