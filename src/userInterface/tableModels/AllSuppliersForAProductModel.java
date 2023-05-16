package userInterface.tableModels;

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
        columnNames.add("prix du produit (en €)");
        columnNames.add("délai de livraison (en jours)");
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
        SupplierForAProduct supplierForAProduct = contents.get(rowIndex);
        switch (columnIndex) {
            case 0 : return supplierForAProduct.getName();
            case 1 : return supplierForAProduct.getReference();
            case 2 : return supplierForAProduct.getProductPrice();
            case 3 : return supplierForAProduct.getDeliveryDelayDays();
            case 4 : return supplierForAProduct.getStatus();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) { return columnNames.get(columnIndex);}

    public Class getColumnClass (int columnIndex) {
        Class columnClass = switch (columnIndex) {
            case  3 -> Integer.class;
            case 2 -> Double.class;
            default -> String.class;
        };
        return columnClass;
    }
}

