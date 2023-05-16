package userInterface.searchs;

import controller.ShopController;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import model.Product;
import model.ProductType;
import userInterface.tableModels.AllProductsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ProductOutOfStockSearchPanel extends JPanel {

    private ShopController shopController;

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

    private ArrayList<ProductType> productTypesList;

    // usefull here to be called in a function later but not in the constructor
    private AllProductsModel allfoundProductsModel;
    private ListSelectionModel listSelect;


    public ProductOutOfStockSearchPanel() {
        this.setLayout(new BorderLayout());

        this.shopController = new ShopController();

        try {
            // panels
            titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout());

            formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(3,2, 10, 10));

            tablePanel = new JPanel();
            tablePanel.setLayout(new BorderLayout());

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BorderLayout());

            // listeners
            ButtonListener buttonListener = new ButtonListener();
            CheckBoxListener checboxListener = new CheckBoxListener();

            // modules for the first search
            titleLabel = new JLabel("<html><p style=\"text-align: center;\">" +
                                        "Recherche des produits en ruptures dans le stock et leurs fournisseurs." +
                                        "<br>Remplissez le formulaire et cliquez sur le bouton." +
                                        "</p><html>");
            titlePanel.add(titleLabel);


            productTypeCheckBoxLabel = new JLabel("Rechercher dans un seul type de produit : ");
            productTypeCheckBoxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            byProductTypeCheckBox = new JCheckBox("Oui");
            byProductTypeCheckBox.addItemListener(checboxListener);
            formPanel.add(productTypeCheckBoxLabel);
            formPanel.add(byProductTypeCheckBox);

            productTypeComboBoxLabel = new JLabel("Type de produits : ");
            productTypeComboBoxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            productTypesList = shopController.getAllProductType();
            String [] productTypesNamesList = new String[productTypesList.size()];
            for (int iProductType = 0; iProductType < productTypesList.size(); iProductType++) {
                productTypesNamesList[iProductType] = productTypesList.get(iProductType).getLabel()
                                                        + "(ref:"
                                                        + productTypesList.get(iProductType).getReference()
                                                        + ')';
            }
            productTypeComboBox = new JComboBox(productTypesNamesList);
            productTypeComboBox.setEnabled(false);
            formPanel.add(productTypeComboBoxLabel);
            formPanel.add(productTypeComboBox);

            searcProductshButton = new JButton("Rechercher le(s) produit(s) : ");
            searcProductshButton.addActionListener(buttonListener);
            buttonsPanel.add(searcProductshButton);

            // second step modules
            searchSupplierButton = new JButton("Rechercher le(s) fournisseur(s) : ");
            searchSupplierButton.addActionListener(buttonListener);

            // add the panels
            this.add(titlePanel, BorderLayout.NORTH);
            this.add(formPanel, BorderLayout.CENTER);
            this.add(buttonsPanel, BorderLayout.SOUTH);
        } catch (GetDatasException exception) {
            showErrorMessageAndPanel("<html><p>La recherche des types de produits n'a pas été possible," +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>Message d'erreur : " + exception.getMessage() + "</p></html>",
                    "erreur : " + exception.getMessage());

        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>La connection aux données n'a pas pu être établie" +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>Message d'erreur : " + exception.getMessage()+ "</p></html>",
                    "erreur : " + exception.getMessage());
        }

        this.setVisible(true);
    }

    public boolean isFormValid() {
        if (byProductTypeCheckBox.isSelected() && productTypeComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "si la case est cochée, un type de produit doit être sélectionné",
                    "erreur de formulaire", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void searchProducts() {
        try {
            if (byProductTypeCheckBox.isSelected()) {
                foundProducts = shopController.getAllProductOutOfMinimumStock(productTypesList.get(productTypeComboBox.getSelectedIndex()));
            } else {
                foundProducts = shopController.getAllProductOutOfMinimumStock();
            }

            if (foundProducts != null && foundProducts.size() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Il n'y aucun produit en rupture" + (byProductTypeCheckBox.isSelected()?
                                "parmis les produits du type sélectionné.":
                                "parmis tout les produits enregistrés."),
                        "Aucun produits", JOptionPane.INFORMATION_MESSAGE);
            } else {
                allfoundProductsModel = new AllProductsModel(foundProducts);
                JTable foundProductsTable = new JTable(allfoundProductsModel);
                foundProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listSelect = foundProductsTable.getSelectionModel();

                JScrollPane foundProductScrollPane = new JScrollPane(foundProductsTable);

                titleLabel.setText("Selectionnez le produit dont vous voulez trouver le(s) fournisseur(s).");

                formPanel.removeAll();
                formPanel.add(foundProductScrollPane, BorderLayout.CENTER);

                buttonsPanel.removeAll();
                buttonsPanel.add(searchSupplierButton);

                // refresh the panel
                this.revalidate();
                this.repaint();
            }
        } catch (GetDatasException exception) {
            showErrorMessageAndPanel("<html><p>La recherche des produits n'a pas été possible," +
                            "<br>veuillez réessayer ultérieurment." +
                            "<br>Message d'erreur : " + exception.getMessage() + "</p></html>",
                    "erreur : " + exception.getMessage());

        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>La connection aux données n'a pas pu être établie" +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application." +
                            "<br>Message d'erreur : " + exception.getMessage()+ "</p></html>",
                    "erreur : " + exception.getMessage());
        }
    }

    private void searchSuppliers() {
        if (listSelect.getMinSelectionIndex() != -1) {
            Product selectedProduct = foundProducts.get(listSelect.getMinSelectionIndex());
            this.removeAll();
            this.add(new SuppliersForAProductSearchPanel(selectedProduct));
            this.revalidate();
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veuillez selectionner un produit pour chercher son/ses fournisseure(s).",
                    "Selectionnez un produit", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * do a JOptionPane.showMessageDialog() to show the error or problem
     * and add an error JLabel on the panel
     * @param message : the message in the panel
     * @param optionPaneMessage : the message in the JOptionPane.showMessageDialog()
     **/
    private void showErrorMessageAndPanel(String message, String optionPaneMessage) {
        JLabel errorLabel = new JLabel(message);
        this.add(errorLabel, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, optionPaneMessage,
                "Problème pour la recherche", JOptionPane.ERROR_MESSAGE);
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
