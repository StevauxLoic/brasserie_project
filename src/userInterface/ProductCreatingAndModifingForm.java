package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
        hasAlcoholCheckBox.addItemListener(new ChecboxListener());
        
        // text fields
        nameTextField = new JTextField();
        referenceTextField = new JTextField();
        vatTextField = new JTextField();
        quantityInStockTextField = new JTextField();
        minimumQuantityInStockTextField = new JTextField();
        alcoholLevelTextField = new JTextField();
        priceTextField = new JTextField();
        descriptionTextField = new JTextField();
        
        TextFieldListener textFieldListener = new TextFieldListener();
        nameTextField.addActionListener(textFieldListener);
        referenceTextField.addActionListener(textFieldListener);
        vatTextField.addActionListener(textFieldListener);
        quantityInStockTextField.addActionListener(textFieldListener);
        minimumQuantityInStockTextField.addActionListener(textFieldListener);
        alcoholLevelTextField.addActionListener(textFieldListener);
        priceTextField.addActionListener(textFieldListener);
        descriptionTextField.addActionListener(textFieldListener);

        // JComboBox
        productTypeComboBox = new JComboBox(new String[]{"spiritueu", "bière", "soda", "whisky"});

        // spinners
        launchingDateSpinner = new JSpinner();

        // fill the panel and display it
        fillFormPanel(formPanel);
        fillButtonsPanel(buttonsPanel);

        this.add(formPanel);
        this.add(buttonsPanel);

        this.setVisible(true);
    }

    private void fillFormPanel(JPanel formPanel) {
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

    public abstract void fillButtonsPanel(JPanel buttonsPanel);

    public JCheckBox getIsSparklingCheckBox() {
        return isSparklingCheckBox;
    }

    public JCheckBox getHasAlcoholCheckBox() {
        return hasAlcoholCheckBox;
    }

    public JComboBox getProductTypeComboBox() {
        return productTypeComboBox;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getReferenceTextField() {
        return referenceTextField;
    }

    public JTextField getVatTextField() {
        return vatTextField;
    }

    public JTextField getQuantityInStockTextField() {
        return quantityInStockTextField;
    }

    public JTextField getMinimumQuantityInStockTextField() {
        return minimumQuantityInStockTextField;
    }

    public JTextField getAlcoholLevelTextField() {
        return alcoholLevelTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getDescriptionTextField() {
        return descriptionTextField;
    }

    public JSpinner getLaunchingDateSpinner() {
        return launchingDateSpinner;
    }

    private boolean isValideDouble (String textToVerify) {
        try {
            double reference = Double.parseDouble(textToVerify);
            return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "l'entrée doit être un nombre à virgule", "erreure d'entrée", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private boolean isValideInt (String textToVerify) {
        try {
            int reference = Integer.parseInt(textToVerify);
            return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "l'entrée doit être un nombre entier", "erreure d'entrée", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    private void showTextFieldError(String message, JTextField textField) {
        textField.setText(null);
        JOptionPane.showMessageDialog(null, message, "erreur d'entrée", JOptionPane.WARNING_MESSAGE);

    }

    public Product readForm() {
        // lis les champs et renvoie le produit en fonction de et renvoie null si au moins un champs est mal rempli

        return null;
    }

    private class ChecboxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            boolean hasAlcohol = event.getStateChange() == ItemEvent.SELECTED;
            ProductCreatingAndModifingForm.this.getAlcoholLevelTextField().setEnabled(hasAlcohol);
        }
    }

    private class TextFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JTextField eventSource = (JTextField) event.getSource();

            String textFieldText = eventSource.getText();
            if(eventSource == nameTextField) {
                if (textFieldText.length() > 180 || textFieldText.length() == 0) {
                    showTextFieldError("le nom doit contenir entre 0 et 180 caractères", eventSource);
                }
            } else if(eventSource == referenceTextField) {
                if (textFieldText.length() > 10 || textFieldText.length() == 0) {
                    showTextFieldError("la référence doit contenir entre 0 et 10 caractères", eventSource);
                }
            } else if(eventSource == descriptionTextField) {
                if (textFieldText.length() < 1) {
                    eventSource.setText(null);
                }
            } else {
                if(eventSource == vatTextField) {
                    if (!isValideDouble(textFieldText) || Double.valueOf(textFieldText) > 100 || Double.valueOf(textFieldText) < 0) {
                        showTextFieldError("la tva doit être un nombre (à virgule ou non) entre 0 et 100", eventSource);
                    }
                } else if(eventSource == alcoholLevelTextField) {
                    if (!isValideDouble(textFieldText) || Double.valueOf(textFieldText) > 100 || Double.valueOf(textFieldText) <= 0) {
                        showTextFieldError("le taux d'alcool doit être un nombre (à virgule ou non) entre 0 et 100 % (100 compris mais 0 non), si le produit n'est pas alcoolisé, ne coché pas la case qui dit que le produit l'est", eventSource);
                    }
                } else if(eventSource == priceTextField) {
                    if (!isValideDouble(textFieldText) || Double.valueOf(textFieldText) < 0) {
                        showTextFieldError("le prix doit être un nombre (à virgule ou non) non négatif", eventSource);
                    }
                } else if(eventSource == quantityInStockTextField) {
                    if (!isValideInt(textFieldText) || Integer.valueOf(textFieldText) < 0) {
                        showTextFieldError("la quantité en stock doit être un nombre entier positif ou nul", eventSource);
                    }
                } else if(eventSource == minimumQuantityInStockTextField) {
                    if (!isValideInt(textFieldText) || Integer.valueOf(textFieldText) < 0) {
                        showTextFieldError("la quantité minimum en stock doit être un nombre entier positif ou nul", eventSource);
                    }
                }
            }
        }
    }
}
