package userInterface;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/*
    this class is a panel where you can search a product then you can delete or modify it (and/or show the reference of it in a pop-up)

    it will first show the form, when completed, if the search button is pushed and the product foun, there will be the informations of it
    then buttons under it allow the user to cancel, modify the product, delete the product or sho its reference in a pop-up
    depending on choice, the whole Panel will change
*/

public class FindProductPanel extends JPanel {
    private JPanel titlePanel, tablePanel;
    private JLabel panelTitleLabel, productTypeComboBoxLabel, productsComboBoxLabel, productReferenceTextFieldLabel;
    private JButton searchButton, modifyButton, deleteButton, goBackToSearchButton;
    private JTable productsTable;

    private ButtonListener buttonListener;
    private Product foundProduct;

    private static Product testProduct;


    // TODO a supprimer quand on en aura plus besoin pour les tests
    static {
        try {
            testProduct = new Product("productReference test", 14,"productName test", 21, 20, false, LocalDate.of(2004, 11, 14), 15.5, 15.5, "productDescritption test", 12);
        } catch (Exception exeption) {
            System.out.println(exeption.getMessage());
        }
    }

    public FindProductPanel() {
        this.setLayout(new BorderLayout());

        //Panel
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        // first step button : search
        this.buttonListener = new ButtonListener();
        searchButton = new JButton("rechercher");
        searchButton.addActionListener(buttonListener);

        // second step buttons
        // go back button
        goBackToSearchButton = new JButton("Retour");
        goBackToSearchButton.addActionListener(buttonListener);

        // delete button
        deleteButton = new JButton("Suprimer");
        deleteButton.addActionListener(buttonListener);

        // modify button
        modifyButton = new JButton("Modifier");
        modifyButton.addActionListener(buttonListener);




        // labels
        panelTitleLabel = new JLabel("Formulaire de recherche d'un produit (par référence ou type de produit)");
        productTypeComboBoxLabel = new JLabel("type de produit : ");
        productsComboBoxLabel = new JLabel("produits : ");
        productReferenceTextFieldLabel = new JLabel("référence du produit : ");

        // Panels filling
        // TODO fill the panel

        this.setVisible(true);
    }


    private void showProductInfosAndButtonsPanels(Product productTodisplay) {
        // the productToDisplay could have been set earlier but by placing it in this function, the code line is just
        // here and not in two places in the function 'searchProduct'
        // it's because the search can be done by the reference or by product type, these two ways use
        // this function 'showProductInfosAndButtonsPanels' so we chose to use productToDisplay as a parameter in first place

        this.removeAll();
        this.foundProduct = productTodisplay;

        this.add(new ProductInfosPanel(productTodisplay), BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout());
        buttonsPanel.add(goBackToSearchButton);

        if (productTodisplay != null) {
            buttonsPanel.add(deleteButton);
            buttonsPanel.add(modifyButton);
        }

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void displayModifyForm() {
        this.removeAll();
        this.add(new ProductModifyingForm(foundProduct));
        this.revalidate();
        this.repaint();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            FindProductPanel thisProductSearchPanel = FindProductPanel.this;
             if (source == deleteButton){
                // TODO supression
                JOptionPane.showMessageDialog(null, "Suppression du produit " + foundProduct.getName(), "suppression de produit", JOptionPane.WARNING_MESSAGE);
            } else if (source == modifyButton) {
                FindProductPanel.this.displayModifyForm();
            } else {
                thisProductSearchPanel.repaint();
                thisProductSearchPanel.revalidate();
            }
        }
    }
}