package userInterface;

import Controller.ShopController;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;
import model.Product;
import model.SupplierForAProduct;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class SuppliersForAProductSearch extends JPanel {

    private ShopController shopController;

    private JPanel titlePanel,
                    formPanel,
                    buttonsPanel;
    private JLabel titleLabel,
            maximumPriceCheckBoxLabel,
            maximumPriceSliderLabel,
            maximumDeliveryDaysCheckBoxLabel,
                    maximumDeliveryDaysSpinnerLabel;
    private JSlider maximumPriceSlider;
    private JSpinner maximumDeliveryDaysSpinner;
    private JCheckBox maximumPriceCheckBox,
                    maximumDeliveryDaysCheckBox;
    private JButton searchSupplierButton;

    private Product selectedProduct;


    public SuppliersForAProductSearch(Product selectedProduct) {
        this.setLayout(new BorderLayout());

        this.selectedProduct = selectedProduct;
        this.shopController = new ShopController();

        // listeners
        ButtonListener buttonListener = new ButtonListener();
        ChecboxListener checboxListener = new ChecboxListener();
        SliderListener sliderListener = new SliderListener();

        // panles
        titlePanel = new JPanel(new BorderLayout());

        formPanel = new JPanel(new GridLayout(4,2));

        buttonsPanel = new JPanel(new BorderLayout());

        // modules
        titleLabel = new JLabel("recherche de fournisseur(s) pour " + selectedProduct.getName());
        titlePanel.add(titleLabel);


        maximumPriceCheckBoxLabel = new JLabel("chercher avec un prix maximum");
        maximumPriceCheckBox = new JCheckBox("oui");
        maximumPriceCheckBox.addItemListener(checboxListener);
        formPanel.add(maximumPriceCheckBoxLabel);
        formPanel.add(maximumPriceCheckBox);

        maximumPriceSliderLabel = new JLabel("prix maximum du fournisseur pour ce produit");
        maximumPriceSlider = new JSlider(0,1000, 10);
        // slider : visual options
        maximumPriceSlider.setPaintTicks(true);   // displays the dots (thin and thinks)
        maximumPriceSlider.setPaintLabels(true);  // displays the numbers on the big dots
        maximumPriceSlider.setMajorTickSpacing(100); // thick dots
        maximumPriceSlider.setMinorTickSpacing(10);  // thin dots
        maximumPriceSlider.setEnabled(false);
        maximumPriceSlider.addChangeListener(sliderListener);
        formPanel.add(maximumPriceSliderLabel);
        formPanel.add(maximumPriceSlider);

        maximumDeliveryDaysCheckBoxLabel = new JLabel("chercher avec un délai de livraison maximum");
        maximumDeliveryDaysCheckBox = new JCheckBox("oui");
        maximumDeliveryDaysCheckBox.addItemListener(checboxListener);
        formPanel.add(maximumDeliveryDaysCheckBoxLabel);
        formPanel.add(maximumDeliveryDaysCheckBox);

        maximumDeliveryDaysSpinnerLabel = new JLabel("nombre de jour maximum pour être livré");
        maximumDeliveryDaysSpinner = new JSpinner();
        maximumDeliveryDaysSpinner.setEnabled(false);
        formPanel.add(maximumDeliveryDaysSpinnerLabel);
        formPanel.add(maximumDeliveryDaysSpinner);


        searchSupplierButton = new JButton("chercher le(s) fournisseur(s)");
        searchSupplierButton.addActionListener(buttonListener);
        buttonsPanel.add(searchSupplierButton);

        // add the panels
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public boolean isFormValid() {
        if (maximumDeliveryDaysCheckBox.isSelected() && (int) maximumDeliveryDaysSpinner.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "si vous sélectionnez un délai maximum pour la livraison, il doit être supérieur à 0", "erreur de nombres de jours pour livraison", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void searchSuppliers() {
        ArrayList<SupplierForAProduct> foundSuppliers;

        try {
            foundSuppliers = shopController.getAllSupplierForAProduct(selectedProduct,
                    (maximumDeliveryDaysCheckBox.isSelected()?(Integer) maximumDeliveryDaysSpinner.getValue():null),
                    (maximumPriceCheckBox.isSelected()?Double.valueOf(maximumPriceSlider.getValue()):null));

            if (foundSuppliers.size() == 0) {
                JOptionPane.showMessageDialog(null, "aucun fournisseur qui vend ce produit n'a été trouvé",
                        "aucun fournisseur", JOptionPane.INFORMATION_MESSAGE);
            } else {
                AllSuppliersForAProductModel allSuppliersForAProductModel = new AllSuppliersForAProductModel(foundSuppliers);
                JTable foundSuppliersTable = new JTable(allSuppliersForAProductModel);
                foundSuppliersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                JScrollPane foundSupplierScrollPane = new JScrollPane(foundSuppliersTable);

                formPanel.removeAll();
                formPanel.add(foundSupplierScrollPane);

                buttonsPanel.removeAll();

                // refresh the panel
                this.repaint();
                this.revalidate();
            }
        } catch (SelectException exception) {
            JOptionPane.showMessageDialog(null, "erreur : " + exception.getMessage(),
                    "erreur de recherche", JOptionPane.ERROR_MESSAGE);

        } catch (CreateConnectionException exception) {
            JOptionPane.showMessageDialog(null, "erreur : " + exception.getMessage(),
                    "erreur de connexion aux données", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ChecboxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            boolean isBoxSelected = event.getStateChange() == ItemEvent.SELECTED;
            Object source = event.getSource();
            SuppliersForAProductSearch thisPanel = SuppliersForAProductSearch.this;

            if (source == thisPanel.maximumDeliveryDaysCheckBox){
                thisPanel.maximumDeliveryDaysSpinner.setEnabled(isBoxSelected);
            } else {
                thisPanel.maximumPriceSlider.setEnabled(isBoxSelected);
            }
        }
    }

    private class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            boolean isSelected = event.getStateChange() == ItemEvent.SELECTED;
            SuppliersForAProductSearch thisPanel = SuppliersForAProductSearch.this;
            Object source = event.getSource();
            if (source == thisPanel.maximumDeliveryDaysCheckBox) {
                thisPanel.maximumDeliveryDaysSpinner.setEnabled(isSelected);
            } else {
                thisPanel.maximumPriceSlider.setEnabled(isSelected);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            SuppliersForAProductSearch thisPanel = SuppliersForAProductSearch.this;
            if (thisPanel.isFormValid()) {
                thisPanel.searchSuppliers();
            }
        }
    }

    private class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {
            SuppliersForAProductSearch thisPanel = SuppliersForAProductSearch.this;
            thisPanel.maximumPriceSliderLabel.setText("prix maximum du fournisseur pour ce produit : " + thisPanel.maximumPriceSlider.getValue() + "€");

        }
    }
}
