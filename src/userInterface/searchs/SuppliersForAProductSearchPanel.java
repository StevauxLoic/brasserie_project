package userInterface.searchs;

import controller.ShopController;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import model.Product;
import model.SupplierForAProduct;
import userInterface.tableModels.AllSuppliersForAProductModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class SuppliersForAProductSearchPanel extends JPanel {

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


    public SuppliersForAProductSearchPanel(Product selectedProduct) {
        this.setLayout(new BorderLayout());

        this.selectedProduct = selectedProduct;
        this.shopController = new ShopController();

        // listeners
        ButtonListener buttonListener = new ButtonListener();
        ChecboxListener checboxListener = new ChecboxListener();
        SliderListener sliderListener = new SliderListener();

        // panles
        titlePanel = new JPanel(new FlowLayout());

        formPanel = new JPanel(new GridLayout(4,2, 10, 10));

        buttonsPanel = new JPanel(new BorderLayout());

        // modules
        titleLabel = new JLabel("<html><p style=\"text-align: center;\">" +
                                    "Recherche de fournisseur(s) pour " + selectedProduct.getName() +
                                    "<br>Il manque actuelement au moins " + getMissingProductsAmount() + "produtis en stock." +
                                    "<br>Remplissez le formulaire puis cliquez sur le bouton pour faire la recherche" +
                                    "</p></html>");
        titlePanel.add(titleLabel);


        maximumPriceCheckBoxLabel = new JLabel("Chercher avec un prix maximum : ");
        maximumPriceCheckBoxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        maximumPriceCheckBox = new JCheckBox("Oui");
        maximumPriceCheckBox.addItemListener(checboxListener);
        formPanel.add(maximumPriceCheckBoxLabel);
        formPanel.add(maximumPriceCheckBox);

        maximumPriceSliderLabel = new JLabel("Prix maximum du fournisseur pour ce produit : ");
        maximumPriceSliderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        maximumPriceSlider = new JSlider(0,100, 10);
        // slider : visual options
        maximumPriceSlider.setPaintTicks(true);   // displays the dots (thin and thinks)
        maximumPriceSlider.setPaintLabels(true);  // displays the numbers on the big dots
        maximumPriceSlider.setMajorTickSpacing(10); // thick dots
        maximumPriceSlider.setMinorTickSpacing(2);  // thin dots
        maximumPriceSlider.setEnabled(false);
        maximumPriceSlider.addChangeListener(sliderListener);
        formPanel.add(maximumPriceSliderLabel);
        formPanel.add(maximumPriceSlider);

        maximumDeliveryDaysCheckBoxLabel = new JLabel("Chercher avec un délai de livraison maximum : ");
        maximumDeliveryDaysCheckBoxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        maximumDeliveryDaysCheckBox = new JCheckBox("Oui");
        maximumDeliveryDaysCheckBox.addItemListener(checboxListener);
        formPanel.add(maximumDeliveryDaysCheckBoxLabel);
        formPanel.add(maximumDeliveryDaysCheckBox);

        maximumDeliveryDaysSpinnerLabel = new JLabel("Nombre de jour maximum pour être livré : ");
        maximumDeliveryDaysSpinnerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        maximumDeliveryDaysSpinner = new JSpinner();
        maximumDeliveryDaysSpinner.setEnabled(false);
        formPanel.add(maximumDeliveryDaysSpinnerLabel);
        formPanel.add(maximumDeliveryDaysSpinner);


        searchSupplierButton = new JButton("Chercher le(s) fournisseur(s) : ");
        searchSupplierButton.addActionListener(buttonListener);
        buttonsPanel.add(searchSupplierButton);

        // add the panels
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public int getMissingProductsAmount() {
        int difference = selectedProduct.getMinimumQuantityInStock() - selectedProduct.getQuantityInStock();
        if (difference > 0) {
            return difference;
        } else {
            return 0;
        }
    }

    public double getAveragePriceForMissingPorducts(ArrayList<SupplierForAProduct> suppliersList) {
        double sumPrices = 0;
        for (int iSupplier = 0; iSupplier < suppliersList.size(); iSupplier++) {
            sumPrices += suppliersList.get(iSupplier).getProductPrice();
        }
        return sumPrices/suppliersList.size();
    }

    public boolean isFormValid() {
        if (maximumDeliveryDaysCheckBox.isSelected() && (int) maximumDeliveryDaysSpinner.getValue() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Si vous sélectionnez un délai maximum pour la livraison, il doit être supérieur à 0.",
                    "Erreur de nombres de jours pour livraison", JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(null,
                        "Aucun fournisseur qui vend ce produit n'a été trouvé. Aucun fournisseur enregistré ne correspond.",
                        "Aucun fournisseur", JOptionPane.INFORMATION_MESSAGE);
            } else {
                formPanel.removeAll();
                buttonsPanel.removeAll();

                double avergePrices = getAveragePriceForMissingPorducts(foundSuppliers);
                int missingProductsAmount = getMissingProductsAmount();
                titleLabel.setText("La moyenne des prix est de : " + avergePrices + "€ pour " + missingProductsAmount + " produits manquants, " +
                                    "celà fait une moyenne de " + avergePrices*missingProductsAmount + "€ pour touts les produits.");

                AllSuppliersForAProductModel allSuppliersForAProductModel = new AllSuppliersForAProductModel(foundSuppliers);
                JTable foundSuppliersTable = new JTable(allSuppliersForAProductModel);
                foundSuppliersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JScrollPane foundSupplierScrollPane = new JScrollPane(foundSuppliersTable);
                formPanel.add(foundSupplierScrollPane);

                // refresh the panel
                this.repaint();
                this.revalidate();
            }
        } catch (GetDatasException exception) {
            JOptionPane.showMessageDialog(null,
                    "Une érreur est survenue lors de la recherche des données sur les fournisseur du produit sélectionné.",
                    "Erreur de recherche", JOptionPane.ERROR_MESSAGE);

        } catch (CreateConnectionException exception) {
            JOptionPane.showMessageDialog(null,
                    "Une érreur est survenue lors de la création d'une connection aux données.",
                    "Erreur de connexion aux données", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ChecboxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            boolean isBoxSelected = event.getStateChange() == ItemEvent.SELECTED;
            Object source = event.getSource();
            SuppliersForAProductSearchPanel thisPanel = SuppliersForAProductSearchPanel.this;

            if (source == thisPanel.maximumDeliveryDaysCheckBox){
                thisPanel.maximumDeliveryDaysSpinner.setEnabled(isBoxSelected);
            } else {
                thisPanel.maximumPriceSlider.setEnabled(isBoxSelected);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            SuppliersForAProductSearchPanel thisPanel = SuppliersForAProductSearchPanel.this;
            if (thisPanel.isFormValid()) {
                thisPanel.searchSuppliers();
            }
        }
    }

    private class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {
            SuppliersForAProductSearchPanel thisPanel = SuppliersForAProductSearchPanel.this;
            thisPanel.maximumPriceSliderLabel.setText("Prix maximum du fournisseur pour ce produit : " +
                    thisPanel.maximumPriceSlider.getValue() + "€ : ");

        }
    }
}
