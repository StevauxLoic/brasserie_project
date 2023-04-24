package userInterface;

import javax.swing.*;
import java.awt.*;

public abstract class ProductCreatingAndModifingForm extends JPanel {
    JLabel nameLabel,
            referenceLabel,
            productTypeLabel,
            vatLabel,
            quantityInStockLabel,
            minimumQuantityInStockLabel,
            sparklingLabel,
            hasAlcoholCheckBoxLabel,
            alcoholLevelLabel,
            priceLabel,
            launchingDateLabel,
            descriptionLabel;

    JPanel formPanel, buttonsPanel;
    JButton resetFormButton;
    JCheckBox isSparklingCheckBox, hasAlcoholCheckBox;

    public ProductCreatingAndModifingForm() {
        this.setLayout(new BorderLayout());

        // panels
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(12,2, 10, 10));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        // labels
        nameLabel = new JLabel("nom");

        referenceLabel = new JLabel("référence");

        productTypeLabel = new JLabel("type de product");

        vatLabel = new JLabel("TVA");

        quantityInStockLabel = new JLabel("quantité en stock");

        minimumQuantityInStockLabel = new JLabel("quantité minimum en stock");

        sparklingLabel = new JLabel("est pétillant");

        alcoholLevelLabel = new JLabel("niveau d'alcool");

        priceLabel = new JLabel("prix HTVA (en magasin)");

        launchingDateLabel = new JLabel("date de lancement");

        descriptionLabel = new JLabel("description");

        hasAlcoholCheckBoxLabel = new JLabel("contient de l'alcool");

        // button
        resetFormButton = new JButton();

        // checkBoxes
        isSparklingCheckBox = new JCheckBox();

        hasAlcoholCheckBox = new JCheckBox();
    }
}
