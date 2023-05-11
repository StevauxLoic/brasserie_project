package userInterface;

import javax.swing.*;
import java.awt.*;

public class ProductOutOfStockAndSupplierSearchPanel extends JPanel {
/*
-	Une case « rechercher un seul type de produit » (JCheckBox)
-	Une liste des types de produits existants (JComboBox)
-	Un bouton « rechercher les produits » (JButton)

==> tab : productTable

-	Une liste des produits trouvés dans l’étape 1 (JComboBox)
-	Une case « prix maximum » (JCheckBox)
-	Une entrée textuelle d’un nombre « prix maximum » (JSlider)
-	Une case « délai de livraison maximum » (JCheckBox)
-	Une entrée textuelle d’un nombre « délai de livraison maximum (en jour) » (JField)
-	Un bouton « rechercher les fournisseurs » (JButton)

==> tab : suppliersForAProductTable

*/

    private JCheckBox byProductTypeChecckBox,
                        withAMaximumPriceCheckBox,
                        withMaximumDeliveryDaysCheckBox;
    private JComboBox productTypeComboBox;
    private JButton searcProductshButton,
                    searchSupplierButton;
    private JSlider minimumPriceSlider;
    private JSpinner MaximumDeliveryDaysSpinner;
    private JPanel titlePanel,
                    formPanel,
                    tablePanel,
                    buttonsPanel;
    private JLabel productTypeCheckBoxLabel,
                    productTypeComboBoxLabel,
                    MaximumPriceCheckBoxLabel,
                    MaximumPriceSpinnerLabel,
                    MaximumDeliveryDaysCheckBoxLabel,
                    MaximumDeliveryDaysSliderLabel,
                    titleLabel;


    public ProductOutOfStockAndSupplierSearchPanel() {
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


    }
}
