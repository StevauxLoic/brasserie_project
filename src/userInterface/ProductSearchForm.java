package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProductSearchForm extends JPanel {
    JPanel titlePanel, formPanel;
    JComboBox productTypeComboBox, productsComboBox;
    JTextField productReferenceTextField;
    JButton resetButton, searchButton;
    JLabel panelTitleLabel, productTypeComboBoxLabel, productsComboBoxLabel, productReferenceTextFieldLabel;

    public ProductSearchForm() {
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
        ButtonListener buttonListener = new ButtonListener();
        resetButton = new JButton("recommencer");
        resetButton.addActionListener(buttonListener);
        searchButton = new JButton("rechercher");
        searchButton.addActionListener(buttonListener);

        // labels
        panelTitleLabel = new JLabel("Formulaire de recherche d'un produit (par référence ou type de produit)");
        productTypeComboBoxLabel = new JLabel("type de produit : ");
        productsComboBoxLabel = new JLabel("produits : ");
        productReferenceTextFieldLabel = new JLabel("référence du produit : ");

        // Panels filling
        titlePanel.add(panelTitleLabel);

        formPanel.add(productTypeComboBoxLabel);
        formPanel.add(productTypeComboBox);

        formPanel.add(productsComboBoxLabel);
        formPanel.add(productsComboBox);

        formPanel.add(productReferenceTextFieldLabel);
        formPanel.add(productReferenceTextField);

        formPanel.add(resetButton);
        formPanel.add(searchButton);

        resetForm();;

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
                // show product and more
            } else {
                JOptionPane.showMessageDialog(null, "Vous devez sélectionner un produit pour rechercher", "recherche impossible", JOptionPane.WARNING_MESSAGE);
            }
        } else if (productReferenceTextField.getText().length() > 0) {
            // show product and more
        } else {
            JOptionPane.showMessageDialog(null, "Vous devez entrer une référence ou sélectionner un type de profuit anisi qu'un produit pour rechercher", "recherche impossible", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == resetButton) {
                ProductSearchForm.this.resetForm();
            } else if (source == searchButton) {
                ProductSearchForm.this.searchProduct();
            }
        }
    }

    private class TextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductSearchForm thisForm = ProductSearchForm.this;
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
            ProductSearchForm thisForm = ProductSearchForm.this;
            if (event.getStateChange() == ItemEvent.SELECTED) {
                thisForm.productReferenceTextField.setEnabled(false);
                thisForm.productsComboBox.setEnabled(true);
            }
        }
    }
}
/*
-	JComboBox (type de produit)  grisé si une référence est entrée
    -	JComboBox (produits)
     accesible dés que le type à été choisi
-	JField (reference du produit)  grisé si un type de produit est sélectionné
-	JButton (reset)
-	JButton (chercher)
-	Sortie :
-	Infos du produit / pas de produit trouvé à la référence ‘référence donnée’
*/