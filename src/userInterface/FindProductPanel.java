package userInterface;

import Controller.ShopController;
import model.*;
import model.Exeptions.CreateConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * this class is a panel where you can search a product then you can delete or modify it
 * <br>
 * <br>
 * it will first show the table of all products with 2 buttons under it (one to delete the product(s) and one to modify it)
 * <br>
 * if the button to delete is pushed, the selectedProduct is delete and if the modify button is selected, the panel will change
 * to a panel of modification for the selected porduct
*/
public class FindProductPanel extends JPanel {
    private JPanel titlePanel, tablePanel, buttonsPanel;
    private JLabel titleLabel;
    private JButton modifyButton, deleteButton;

    private JTable productsTable;
    private JScrollPane productsScrollPane;

    private ButtonListener buttonListener;
    private AllProductsModel allProductModel;
    private ListSelectionModel listSelect;
    private ArrayList<Product> productsList;

    private ShopController shopController;


    // TODO a supprimer quand on en aura plus besoin pour les tests
    private static Product testProduct, secondTestProduct;
    static {
            testProduct = new Product("productRef", 14,"productName test", 21, 20, false, LocalDate.of(2004, 11, 14), 15.5, 15.5, 12, "productDescritption test");
            secondTestProduct = new Product("txtRef", 2,"second test", 6, 5, true, LocalDate.of(2010, 9, 22), 4, 0, 11);
    }

    public FindProductPanel() {
        this.setLayout(new BorderLayout());

        try {
            this.shopController = new ShopController();

            //Panel
            titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout());

            tablePanel = new JPanel();
            tablePanel.setLayout(new BorderLayout());

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());

            // JTables and scrollPane
            // TODO à supprimer la liste d'exemple
            productsList = new ArrayList<Product>();
            productsList.add(testProduct);
            productsList.add(secondTestProduct);

            // if no table ==> the table and buttons are replace by JLabel that
            // explain the fact that there is not any product found

            if (!productsList.isEmpty()) {
                allProductModel = new AllProductsModel(productsList);
                productsTable = new JTable(allProductModel);
                productsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                listSelect = productsTable.getSelectionModel();

                productsScrollPane = new JScrollPane(productsTable);
                tablePanel.add(productsScrollPane, BorderLayout.CENTER);

                // button Listener
                buttonListener = new ButtonListener();

                // buttons
                deleteButton = new JButton("Suprimer");
                deleteButton.addActionListener(buttonListener);
                buttonsPanel.add(deleteButton);

                modifyButton = new JButton("Modifier");
                modifyButton.addActionListener(buttonListener);
                buttonsPanel.add(modifyButton);

                // labels
                titleLabel = new JLabel("Table des poduits à sélectionner");
                titlePanel.add(titleLabel);

                // add the button pannel

                this.add(buttonsPanel, BorderLayout.SOUTH);
                this.add(tablePanel, BorderLayout.CENTER);

            } else {
                titleLabel = new JLabel("<html><p>aucun produit n'a été trouvé,<br>" +
                        "veuillez reselectionner le menu si vous voulez réessayer<br>" +
                        "peut-être qu'il n' y a juste aucun produits enregistré<br>" +
                        "dans la base de données</p></html>");
                titlePanel.add(titleLabel);
                JOptionPane.showMessageDialog(null, "aucun produit n'a été trouvé, veuillez reselectionner le menu si vous voulez réessayer", "pas de rpoduit trouvé", JOptionPane.INFORMATION_MESSAGE);
            }

            // Panels filling
            this.add(titlePanel, BorderLayout.NORTH);

        } catch (CreateConnectionException e) {
            JOptionPane.showMessageDialog(null, "la création de la connection d'accès aux données a échoué", "erreur de création de connection", JOptionPane.ERROR_MESSAGE);

            JLabel errorLabel = new JLabel("<html><p>la recherche d'un produit n'a pas été possible," +
                                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application</p></html>");
            this.add(errorLabel, BorderLayout.CENTER);
        }


        this.setVisible(true);
    }

    private Product getSelectedAProduct(){
        return productsList.get(listSelect.getMinSelectionIndex());
    }

    private void displayModifyForm() {
        this.removeAll();
        this.add(new ProductModifyingForm(getSelectedAProduct()));
        this.revalidate();
        this.repaint();
    }

    private void deleteProducts() {
        if (listSelect.getMinSelectionIndex() == listSelect.getMaxSelectionIndex()) {
            // TODO del un produit
        } else {
            // TODO del des produits
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            FindProductPanel thisPanel = FindProductPanel.this;
            int firstSelectedRow = thisPanel.listSelect.getMinSelectionIndex();

            if (firstSelectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez d'abord séléctionner un/des produit(s) (un seul pour 'modifier')", "erreur de sélection", JOptionPane.WARNING_MESSAGE);
            } else {
                Object source = event.getSource();
                 if (source == deleteButton){
                    thisPanel.deleteProducts();
                    JOptionPane.showMessageDialog(null, "Suppression du/des produit(s) ", "suppression produit(s)", JOptionPane.INFORMATION_MESSAGE);
                } else if (source == modifyButton) {
                    int lastSelected = thisPanel.listSelect.getMaxSelectionIndex();

                    if (lastSelected != firstSelectedRow) {
                        JOptionPane.showMessageDialog(null, "Un seul élément doit être séléctionné pour être modifié", "erreur de sélection", JOptionPane.WARNING_MESSAGE);
                    } else {
                        thisPanel.displayModifyForm();
                    }
                }
            }
        }
    }
}