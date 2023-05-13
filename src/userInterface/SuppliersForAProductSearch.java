package userInterface;

import model.BusinessEntity;
import model.Product;
import model.SupplierForAProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class SuppliersForAProductSearch extends JPanel {
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

    // TODO a supprimer quand on en aura plus besoin pour les tests
    private static BusinessEntity testSupplier, secondTestSupplier;
    static {
        testSupplier = new SupplierForAProduct("refEntity", "Aqua drinks", 21533L, "supplier");
        secondTestSupplier = new SupplierForAProduct("testN2", "Hot drinks", 72815L, "supplier");
    }

    public SuppliersForAProductSearch(Product selectedProduct) {
        this.selectedProduct = selectedProduct;

        // panles
        titlePanel = new JPanel(new BorderLayout());

        formPanel = new JPanel(new GridLayout(4,2));

        buttonsPanel = new JPanel(new BorderLayout());

        // modules
        titleLabel = new JLabel("recherche de fournisseur(s) pour " + selectedProduct.getName());
        titlePanel.add(titleLabel);


        maximumPriceCheckBoxLabel = new JLabel("chercher avec un prix maximum");
        maximumPriceCheckBox = new JCheckBox("oui");
        formPanel.add(maximumPriceCheckBoxLabel);
        formPanel.add(maximumPriceCheckBox);

        maximumPriceSliderLabel = new JLabel("prix maximum du fournisseur pour ce produit");
        maximumPriceSlider = new JSlider(0, 365);
        maximumPriceSlider.setEnabled(false);
        formPanel.add(maximumPriceSliderLabel);
        formPanel.add(maximumPriceSlider);

        maximumDeliveryDaysCheckBoxLabel = new JLabel("chercher avec un délai de livraison maximum");
        maximumDeliveryDaysCheckBox = new JCheckBox("oui");
        formPanel.add(maximumDeliveryDaysCheckBoxLabel);
        formPanel.add(maximumDeliveryDaysCheckBox);

        maximumDeliveryDaysSpinnerLabel = new JLabel("nombre de jour maximum pour être livré");
        maximumDeliveryDaysSpinner = new JSpinner();
        maximumDeliveryDaysSpinner.setEnabled(false);
        formPanel.add(maximumDeliveryDaysSpinnerLabel);
        formPanel.add(maximumDeliveryDaysSpinner);


        searchSupplierButton = new JButton("chercher le(s) fournisseur(s)");
        buttonsPanel.add(searchSupplierButton);

        // add the panels
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public boolean isFormValid() {
        if ((int) maximumDeliveryDaysSpinner.getValue() <= 0) {
            JOptionPane.showMessageDialog(null, "si vous sélectionné un délai maximum pour la livraison, il doit être supérieur à 0", "erreur de nombres de jours pour livraison", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void searchSuppliers() {
        ArrayList<SupplierForAProduct> foundSuppliers;

        if (maximumPriceCheckBox.isSelected() && maximumDeliveryDaysCheckBox.isSelected()) {
            // TODO recherche avec prix et délai max
        } else if(maximumPriceCheckBox.isSelected()) {
            // TODO recherche avec le prix max
        } else if (maximumDeliveryDaysCheckBox.isSelected()) {
            // TODO recherche avec le délai max
        } else {
            // TODO recherche sans précisions
        }
        // TODO à supp quand les recherches seront bonnes
        foundSuppliers = new ArrayList<SupplierForAProduct>();
        foundSuppliers.add(testSupplier);
        foundSuppliers.add(secondTestSupplier);

        if (foundSuppliers.size() == 0) {
            JOptionPane.showMessageDialog(null, "aucun produits en rupture de stock trouvé", "aucun produits", JOptionPane.INFORMATION_MESSAGE);
        } else {
            AllSuppliersForAProductModel allSuppliersForAProductModel = new AllSuppliersForAProductModel(foundSuppliers);
            JTable foundSuppliersTable = new JTable(allSuppliersForAProductModel);
            foundSuppliersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            ListSelectionModel listSelect = foundSuppliersTable.getSelectionModel();

            JScrollPane foundSupplierScrollPane = new JScrollPane(foundSuppliersTable);

            formPanel.removeAll();
            formPanel.add(foundSupplierScrollPane, BorderLayout.CENTER);

            buttonsPanel.removeAll();
            buttonsPanel.add(searchSupplierButton);
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

}
