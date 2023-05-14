package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductOutOfStockSearchPanel extends JPanel {
/*
==> tab : productTable

-	Une liste des produits trouvés dans l’étape 1 (JComboBox)
-	Une case « prix maximum » (JCheckBox)
-	Une entrée textuelle d’un nombre « prix maximum » (JSlider)
-	Une case « délai de livraison maximum » (JCheckBox)
-	Une entrée textuelle d’un nombre « délai de livraison maximum (en jour) » (JField)
-	Un bouton « rechercher les fournisseurs » (JButton)

==> tab : suppliersForAProductTable

*/

    private JCheckBox byProductTypeCheckBox;

    private JComboBox productTypeComboBox;
    private JButton searcProductshButton,
                    searchSupplierButton;

    private JPanel titlePanel,
                    formPanel,
                    tablePanel,
                    buttonsPanel;
    private JLabel productTypeCheckBoxLabel,
                    productTypeComboBoxLabel,
                    titleLabel;
    private ArrayList <Product> foundProducts;

    // usefull here to be called in a function laterbut not in the constructor
    private AllProductsModel allfoundProductsModel;
    private ListSelectionModel listSelect;

    // TODO a supprimer quand on en aura plus besoin pour les tests
    private static Product testProduct, secondTestProduct;
    static {
        testProduct = new Product("productRef", 14,"productName test", 21, 20, false, LocalDate.of(2004, 11, 14), 15.5, 15.5, 12, "productDescritption test");
        secondTestProduct = new Product("txtRef", 2,"second test", 6, 5, true, LocalDate.of(2010, 9, 22), 4, 0, 11);
    }

    public ProductOutOfStockSearchPanel() {
        this.setLayout(new BorderLayout());

        // panels
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3,2));

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        // listeners
        ButtonListener buttonListener = new ButtonListener();
        CheckBoxListener checboxListener = new CheckBoxListener();

        // modules for the first search
        titleLabel = new JLabel("recherche des produits en ruptures dans le stock et leurs fournisseurs");
        titlePanel.add(titleLabel);


        productTypeCheckBoxLabel = new JLabel("rechercher un seul type de produit");
        byProductTypeCheckBox = new JCheckBox("oui");
        byProductTypeCheckBox.addItemListener(checboxListener);
        formPanel.add(productTypeCheckBoxLabel);
        formPanel.add(byProductTypeCheckBox);

        productTypeComboBoxLabel = new JLabel("type de product");
        // TODO get the types list
        productTypeComboBox = new JComboBox(new String[]{"spiritueu", "bière", "soda", "whisky"});
        productTypeComboBox.setEnabled(false);
        formPanel.add(productTypeComboBoxLabel);
        formPanel.add(productTypeComboBox);


        searcProductshButton = new JButton("rechercher le(s) produit(s)");
        searcProductshButton.addActionListener(buttonListener);
        buttonsPanel.add(searcProductshButton);

        // second step modules
        searchSupplierButton = new JButton("rechercher le(s) fournisseur(s)");
        searchSupplierButton.addActionListener(buttonListener);

        // add the panels
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public boolean isFormValid() {
        if (byProductTypeCheckBox.isSelected() && productTypeComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "si la case est cochée, un type de produit doit être sélectionné", "erreur de formulaire", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void searchProducts() {
        if (byProductTypeCheckBox.isSelected()) {
            // TODO search products in a type
        } else {
            // TODO search all products
        }
        // TODO à supp quand les recherches marcherons
        foundProducts = new ArrayList<Product>();
        foundProducts.add(testProduct);
        foundProducts.add(secondTestProduct);

        if (foundProducts!= null && foundProducts.size() == 0) {
            JOptionPane.showMessageDialog(null, "aucun produits en rupture de stock trouvé", "aucun produits", JOptionPane.INFORMATION_MESSAGE);
        } else {
            allfoundProductsModel = new AllProductsModel(foundProducts);
            JTable foundProductsTable = new JTable(allfoundProductsModel);
            foundProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = foundProductsTable.getSelectionModel();

            JScrollPane foundProductScrollPane = new JScrollPane(foundProductsTable);

            formPanel.removeAll();
            formPanel.add(foundProductScrollPane, BorderLayout.CENTER);

            buttonsPanel.removeAll();
            buttonsPanel.add(searchSupplierButton);

            // refresh the panel
            this.revalidate();
            this.repaint();;
        }
    }

    private void searchSuppliers() {
        if (listSelect.getMinSelectionIndex() != -1) {
            Product selectedProduct = foundProducts.get(listSelect.getMinSelectionIndex());
            this.removeAll();
            this.add(new SuppliersForAProductSearch(selectedProduct));
            this.revalidate();
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "selectionnez un produit pour chercher son/ses fournisseure(s)", "selectionnez un produit", JOptionPane.WARNING_MESSAGE);
        }

    }

    private class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            boolean wantToSearchByType = event.getStateChange() == ItemEvent.SELECTED;
            ProductOutOfStockSearchPanel.this.productTypeComboBox.setEnabled(wantToSearchByType);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            ProductOutOfStockSearchPanel thisPanel = ProductOutOfStockSearchPanel.this;
            if (source == thisPanel.searcProductshButton) {
                if (thisPanel.isFormValid()) {
                    thisPanel.searchProducts();
                }
            } else {
                thisPanel.searchSuppliers();
            }
        }
    }
}
