package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.*;

public abstract class ProductCreatingAndModifingForm extends JPanel {
    private JLabel nameLabel,
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

    private JPanel formPanel, buttonsPanel;
    private JCheckBox isSparklingCheckBox, hasAlcoholCheckBox;
    private JComboBox productTypeComboBox;
    private JTextField nameTextField, 
                        referenceTextField,
                        vatTextField,
                        quantityInStockTextField,
                        minimumQuantityInStockTextField,
                        alcoholLevelTextField,
                        priceTextField,
                        descriptionTextField;
    private JSpinner launchingDateSpinner;

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
        hasAlcoholCheckBoxLabel = new JLabel("contient de l'alcool");
        alcoholLevelLabel = new JLabel("niveau d'alcool");
        priceLabel = new JLabel("prix HTVA (en magasin)");
        launchingDateLabel = new JLabel("date de lancement");
        descriptionLabel = new JLabel("description");
        

        // checkBoxes
        isSparklingCheckBox = new JCheckBox("oui");
        hasAlcoholCheckBox = new JCheckBox("oui");
        
        // text fields
        nameTextField = new JTextField();
        referenceTextField = new JTextField();
        vatTextField = new JTextField();
        quantityInStockTextField = new JTextField();
        minimumQuantityInStockTextField = new JTextField();
        alcoholLevelTextField = new JTextField();
        priceTextField = new JTextField();
        descriptionTextField = new JTextField();

        // JComboBox
        productTypeComboBox = new JComboBox(new String[]{"spiritueu", "bière", "soda", "whisky"});

        // spinners
        launchingDateSpinner = new JSpinner();

        // fill the panel and display it
        fillFormPanel(formPanel);
        fillButtonsPanel();

        this.add(formPanel);
        this.add(buttonsPanel);

        this.setVisible(true);
    }

    private void fillFormPanel(JPanel buttonsPanel) {
        // fill the form panel
        formPanel.add(nameLabel);
        formPanel.add(nameTextField);

        formPanel.add(referenceLabel);
        formPanel.add(referenceTextField);

        formPanel.add(productTypeLabel);
        formPanel.add(productTypeComboBox);

        formPanel.add(vatLabel);
        formPanel.add(vatTextField);

        formPanel.add(quantityInStockLabel);
        formPanel.add(quantityInStockTextField);

        formPanel.add(minimumQuantityInStockLabel);
        formPanel.add(minimumQuantityInStockTextField);

        formPanel.add(sparklingLabel);
        formPanel.add(isSparklingCheckBox);

        formPanel.add(hasAlcoholCheckBoxLabel);
        formPanel.add(hasAlcoholCheckBox);

        formPanel.add(alcoholLevelLabel);
        formPanel.add(alcoholLevelTextField);

        formPanel.add(priceLabel);
        formPanel.add(priceTextField);

        formPanel.add(launchingDateLabel);
        formPanel.add(launchingDateSpinner);

        formPanel.add(descriptionLabel);
        formPanel.add(descriptionTextField);
    }
    public abstract void fillButtonsPanel();

    // return null if ther is a problem (something is not filled)
    /*public Product readForm(){
        if ()
        // Product redProduct = new Product();
    }*/

    public abstract void fillButtonsPanel(JPanel buttonsPanel);
}
