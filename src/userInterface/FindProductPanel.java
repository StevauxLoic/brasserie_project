package userInterface;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

/*
    this class is a panel where you can search a product then you can delete or modify it (and/or show the reference of it in a pop-up)

    it will first show the form, when completed, if the search button is pushed and the product foun, there will be the informations of it
    then buttons under it allow the user to cancel, modify the product, delete the product or sho its reference in a pop-up
    depending on choice, the whole Panel will change
*/

public class FindProductPanel extends JPanel {
    private JPanel titlePanel, formPanel;
    private JComboBox productTypeComboBox, productsComboBox;
    private JTextField productReferenceTextField;
    private JButton resetButton, searchButton, goBackToSearchButton, showReferenceButton, deleteButton, modifyButton;
    private JLabel panelTitleLabel, productTypeComboBoxLabel, productsComboBoxLabel, productReferenceTextFieldLabel;

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
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4,2, 10, 10));

        // ComboBox
        JComboBoxListener jComboBoxListener = new JComboBoxListener();
        productTypeComboBox = new JComboBox(new String[]{"spiritueu", "bière", "soda", "whisky"});
        productTypeComboBox.addItemListener(jComboBoxListener);
        productsComboBox = new JComboBox(new String[]{"jupiler", "champagne", "sprite", "amaretto"});
        // TODO rechercher les types de produits

        // textField
        TextListener textListener = new TextListener();
        productReferenceTextField = new JTextField();
        productReferenceTextField.addActionListener(textListener);

        // button
        this.buttonListener = new ButtonListener();
        resetButton = new JButton("recommencer");
        resetButton.addActionListener(buttonListener);
        searchButton = new JButton("rechercher");
        searchButton.addActionListener(buttonListener);

        goBackToSearchButton = new JButton("Retourner à la page de recherche");
        goBackToSearchButton.addActionListener(buttonListener);

        // labels
        panelTitleLabel = new JLabel("Formulaire de recherche d'un produit (par référence ou type de produit)");
        productTypeComboBoxLabel = new JLabel("type de produit : ");
        productsComboBoxLabel = new JLabel("produits : ");
        productReferenceTextFieldLabel = new JLabel("référence du produit : ");

        // Panels filling
        fillPanelWithForm();

        this.setVisible(true);
    }

    private void fillPanelWithForm() {
        titlePanel.add(panelTitleLabel);

        formPanel.add(productTypeComboBoxLabel);
        formPanel.add(productTypeComboBox);

        formPanel.add(productsComboBoxLabel);
        formPanel.add(productsComboBox);

        formPanel.add(productReferenceTextFieldLabel);
        formPanel.add(productReferenceTextField);

        formPanel.add(resetButton);
        formPanel.add(searchButton);

        resetForm();

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
    }

    private void resetForm() {
        productsComboBox.setSelectedItem(null);
        productsComboBox.setEnabled(false);

        productTypeComboBox.setSelectedItem(null);
        productTypeComboBox.setEnabled(true);

        productReferenceTextField.setText("");
        productReferenceTextField.setEnabled(true);
    }

    private void searchProduct() {
        Object selectedProductType = productTypeComboBox.getSelectedItem();
        if (selectedProductType != null) {
            if (productsComboBox.getSelectedItem() != null) {
                // show product and more TODO search product
                showProductInfosAndButtonsPanels(testProduct);
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez sélectionner un produit pour rechercher", "recherche impossible", JOptionPane.WARNING_MESSAGE);
            }
        } else if (productReferenceTextField.getText().length() > 0) { // TODO to review while checking what a reference contains
            // show product and more TODO search product by reference
            showProductInfosAndButtonsPanels(testProduct);
        } else {
            JOptionPane.showMessageDialog(null, "Vous devez entrer une référence ou sélectionner un type de profuit anisi qu'un produit pour rechercher", "recherche impossible", JOptionPane.WARNING_MESSAGE);
        }
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
        buttonsPanel.add(goBackToSearchButton);

        if (productTodisplay != null) {
            // show reference Button
            this.showReferenceButton = new JButton("Afficher la référence dans un pop-up");
            showReferenceButton.addActionListener(buttonListener);

            buttonsPanel.add(showReferenceButton);

            // delete button
            deleteButton = new JButton("Suprimer");
            deleteButton.addActionListener(buttonListener);

            buttonsPanel.add(deleteButton);

            // modify button
            modifyButton = new JButton("Modifier");
            modifyButton.addActionListener(buttonListener);

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
            if (source == showReferenceButton) {
                ProductReferencePopUp productReferencePopUp = new ProductReferencePopUp(foundProduct.getReference());
            } else if (source == deleteButton){
                // TODO supression
                JOptionPane.showMessageDialog(null, "Suppression du produit " + foundProduct.getName(), "suppression de produit", JOptionPane.WARNING_MESSAGE);
            } else if (source == modifyButton) {
                FindProductPanel.this.displayModifyForm();
            } else {
                if (source == resetButton) {
                    thisProductSearchPanel.resetForm();
                } else if (source == searchButton) {
                    thisProductSearchPanel.searchProduct();
                } else if (source == goBackToSearchButton) {
                    thisProductSearchPanel.removeAll();
                    thisProductSearchPanel.fillPanelWithForm();
                }
                thisProductSearchPanel.repaint();
                thisProductSearchPanel.revalidate();
            }
        }
    }

    private class TextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            FindProductPanel thisForm = FindProductPanel.this;
            if (thisForm.productReferenceTextField.getText() != "") {
                thisForm.productTypeComboBox.setEnabled(false);
            } else if (!thisForm.productTypeComboBox.isEnabled()) {
                thisForm.productTypeComboBox.setEnabled(true);
            }
        }
    }

    private class JComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            FindProductPanel thisForm = FindProductPanel.this;
            if (event.getStateChange() == ItemEvent.SELECTED) {
                thisForm.productReferenceTextField.setEnabled(false);
                thisForm.productsComboBox.setEnabled(true);
                // TODO rechercher les produits du type sélectionné
            }
        }
    }
}