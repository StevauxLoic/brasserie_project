package userInterface;

import Controller.ShopController;
import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.DeleteExeption;
import model.Exeptions.SelectExeption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            productsList = shopController.getAllProduct();

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
                titleLabel = new JLabel("<html><p>Table des poduits à sélectionner" +
                                        "<br>attention la supression suprime égalment d'autres données relative au produit)");
                titlePanel.add(titleLabel);

                // Panels filling
                this.add(titlePanel, BorderLayout.NORTH);
                this.add(buttonsPanel, BorderLayout.SOUTH);
                this.add(tablePanel, BorderLayout.CENTER);

            } else {    // no products found
                showErrorMessageAndPanel("<html><p>aucun produit n'a été trouvé,<br>" +
                        "veuillez reselectionner le menu si vous voulez réessayer<br>" +
                        "peut-être qu'il n' y a juste aucun produits enregistré<br>" +
                        "dans la base de données</p></html>",
                        "aucun produit n'a été trouvé, veuillez reselectionner le menu si vous voulez réessayer");
            }

        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>la connection aux données n'a pas pu être établie" +
                    "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                    "message d'erreur : <br>" +
                    exception.getMessage()+ "</p></html>",
                    "la connection aux données n'a pas pu être établie");
        } catch (SelectExeption exception) {
            showErrorMessageAndPanel("<html><p>la recherche d'un produit n'a pas été possible," +
                    "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application</p></html>",
                    "la recherche d'un produit n'a pas été possible");
        }

        this.setVisible(true);
    }

    /**
    * do a JOptionPane.showMessageDialog() to show the error or problem
    * and add an error JLabel on the panel
    * @param message : the message in the panel
    * @param optionPaneMessage : the message in the JOptionPane.showMessageDialog()
    **/
    private void showErrorMessageAndPanel(String message, String optionPaneMessage) {
        JOptionPane.showMessageDialog(null, optionPaneMessage, "problème pour la recherche", JOptionPane.WARNING_MESSAGE);
        JLabel errorLabel = new JLabel(message);
        this.add(errorLabel, BorderLayout.CENTER);
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
        int firstProductSelectedIndex = listSelect.getMinSelectionIndex();
        if (listSelect.getMinSelectionIndex() == listSelect.getMaxSelectionIndex()) {
            try {
                shopController.deleteProduct(productsList.get(firstProductSelectedIndex));
                JOptionPane.showMessageDialog(null, "le produit a bien été supprimé",
                        "succès de la supression", JOptionPane.INFORMATION_MESSAGE);
                productsList.remove(firstProductSelectedIndex);

            } catch (DeleteExeption exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "erreur de supression d'un produit", JOptionPane.ERROR_MESSAGE);

            } catch (CreateConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "erreur de conexion aux données", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            int lasstProductSelectedIndex = listSelect.getMaxSelectionIndex();
            int deleteingProductIndex = firstProductSelectedIndex;
            // will stop deleting if one throw an error
            try {
                while (deleteingProductIndex <= lasstProductSelectedIndex) {
                    shopController.deleteProduct(productsList.get(deleteingProductIndex));
                    JOptionPane.showMessageDialog(null, "un produit a bien été supprimé",
                            "succès de la supression", JOptionPane.INFORMATION_MESSAGE);
                    productsList.remove(deleteingProductIndex);

                }
                JOptionPane.showMessageDialog(null, "tout les produits ont bien été supprimé",
                        "succès des supressions", JOptionPane.INFORMATION_MESSAGE);

            } catch (DeleteExeption exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "erreur de supression d'un produit", JOptionPane.ERROR_MESSAGE);
            } catch (CreateConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "erreur de conexion aux données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            FindProductPanel thisPanel = FindProductPanel.this;
            int firstSelectedRow = thisPanel.listSelect.getMinSelectionIndex();

            if (firstSelectedRow == -1) {
                JOptionPane.showMessageDialog(null,
                        "Veuillez d'abord séléctionner un/des produit(s) (un seul pour 'modifier')",
                        "erreur de sélection", JOptionPane.WARNING_MESSAGE);

            } else {
                Object source = event.getSource();
                 if (source == deleteButton){
                    thisPanel.deleteProducts();

                } else if (source == modifyButton) {
                    int lastSelected = thisPanel.listSelect.getMaxSelectionIndex();
                    if (lastSelected != firstSelectedRow) {
                        JOptionPane.showMessageDialog(null,
                                "Un seul élément doit être séléctionné pour être modifié",
                                "erreur de sélection", JOptionPane.WARNING_MESSAGE);

                    } else {
                        thisPanel.displayModifyForm();
                    }
                }
            }
        }
    }
}