package userInterface;

import controller.ShopController;
import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This abstract class is for the creat and modifying part of the crud (on the table Product)
 * <br> <br>
 * it will create a whole form with fields, panel, chckboxes,... and have a function to read the form
 * <br>
 * the fields have a listener that check if the value is valid, if it is not, the user will be notified and the value reset
**/
public abstract class ProductCreatingAndModifingFormTemplate extends JPanel {
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
                        alcoholLevelTextField,
                        priceTextField,
                        descriptionTextField;
    // int spinners
    private JSpinner quantityInStockSpinner,
            minimumQuantityInStockSpinner;
    //date spinner
    private JSpinner launchingDateSpinner;

    private Date launchingDateSelected;

    private ShopController shopController;
    private ArrayList<ProductType> productTypesList;

    public ProductCreatingAndModifingFormTemplate() {
        this.setLayout(new BorderLayout());

        this.shopController = new ShopController();

        try {
            // panels
            formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(12,2, 10, 10));

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());

            //listeners
            TextFieldListener textFieldListener = new TextFieldListener();
            ChecboxListener checboxListener = new ChecboxListener();
            SpinnerListener spinnerListener = new SpinnerListener();

            // modules
            nameLabel = new JLabel("nom : ");
            nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            nameTextField = new JTextField();
            nameTextField.addActionListener(textFieldListener);
            formPanel.add(nameLabel);
            formPanel.add(nameTextField);

            referenceLabel = new JLabel("référence : ");
            referenceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            referenceTextField = new JTextField();
            referenceTextField.addActionListener(textFieldListener);
            formPanel.add(referenceLabel);
            formPanel.add(referenceTextField);

            productTypeLabel = new JLabel("type de produit : ");
            productTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            productTypesList = shopController.getAllProductType();
            String [] productTypesNamesList = new String[productTypesList.size()];
            for (int iProductType = 0; iProductType < productTypesList.size(); iProductType++) {
                productTypesNamesList[iProductType] = productTypesList.get(iProductType).getLabel()
                                                    + "(ref:"
                                                    + productTypesList.get(iProductType).getReference()
                                                    + ')';
            }
            productTypeComboBox = new JComboBox(productTypesNamesList);
            productTypeComboBox.setSelectedItem(null);
            formPanel.add(productTypeLabel);
            formPanel.add(productTypeComboBox);

            vatLabel = new JLabel("TVA : ");
            vatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            vatTextField = new JTextField();
            vatTextField.addActionListener(textFieldListener);
            formPanel.add(vatLabel);
            formPanel.add(vatTextField);

            quantityInStockLabel = new JLabel("quantité en stock : ");
            quantityInStockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            quantityInStockSpinner = new JSpinner();
            quantityInStockSpinner.addChangeListener(spinnerListener);
            formPanel.add(quantityInStockLabel);
            formPanel.add(quantityInStockSpinner);

            minimumQuantityInStockLabel = new JLabel("quantité minimum en stock : ");
            minimumQuantityInStockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            minimumQuantityInStockSpinner = new JSpinner();
            minimumQuantityInStockSpinner.addChangeListener(spinnerListener);
            formPanel.add(minimumQuantityInStockLabel);
            formPanel.add(minimumQuantityInStockSpinner);

            sparklingLabel = new JLabel("est pétillant : ");
            sparklingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            isSparklingCheckBox = new JCheckBox("oui");
            formPanel.add(sparklingLabel);
            formPanel.add(isSparklingCheckBox);

            hasAlcoholCheckBoxLabel = new JLabel("contient de l'alcool : ");
            hasAlcoholCheckBoxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            hasAlcoholCheckBox = new JCheckBox("oui");
            hasAlcoholCheckBox.addItemListener(checboxListener);
            formPanel.add(hasAlcoholCheckBoxLabel);
            formPanel.add(hasAlcoholCheckBox);

            alcoholLevelLabel = new JLabel("niveau d'alcool : ");
            alcoholLevelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            alcoholLevelTextField = new JTextField();
            alcoholLevelTextField.setEnabled(false);
            alcoholLevelTextField.addActionListener(textFieldListener);
            formPanel.add(alcoholLevelLabel);
            formPanel.add(alcoholLevelTextField);

            priceLabel = new JLabel("prix HTVA (en magasin) : ");
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            priceTextField = new JTextField();
            priceTextField.addActionListener(textFieldListener);
            formPanel.add(priceLabel);
            formPanel.add(priceTextField);

            launchingDateLabel = new JLabel("date de lancement : ");
            launchingDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            launchingDateSelected = new Date();
            launchingDateSpinner = new JSpinner(new SpinnerDateModel(launchingDateSelected, null, null, Calendar.YEAR));
            JSpinner.DateEditor launchingDateEditor = new JSpinner.DateEditor(launchingDateSpinner,"dd/MM/yyyy");
            launchingDateSpinner.setEditor(launchingDateEditor);
            formPanel.add(launchingDateLabel);
            formPanel.add(launchingDateSpinner);

            descriptionLabel = new JLabel("description (optionnel) : ");
            descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            descriptionTextField = new JTextField();
            descriptionTextField.addActionListener(textFieldListener);
            formPanel.add(descriptionLabel);
            formPanel.add(descriptionTextField);

            // fill the panel and display it
            fillButtonsPanel(buttonsPanel);

            this.add(formPanel, BorderLayout.CENTER);
            this.add(buttonsPanel, BorderLayout.SOUTH);
        } catch (GetDatasException exception) {
            showErrorMessageAndPanel("<html><p>la recherche des types de produits n'a pas été possible," +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>erreur : " + exception.getMessage() + "</p></html>",
                    "erreur : " + exception.getMessage());

        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>la connection aux données n'a pas pu être établie" +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>erreur : " + exception.getMessage()+ "</p></html>",
                    "erreur : " + exception.getMessage());
        }

        this.setVisible(true);
    }


    // getters
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

    public JSpinner getQuantityInStockSpinner() {
        return quantityInStockSpinner;
    }

    public JSpinner getMinimumQuantityInStockSpinner() {
        return minimumQuantityInStockSpinner;
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

    public Date getLaunchingDateSelected() {
        return ((Date) getLaunchingDateSpinner().getValue());
    }

    public ShopController getShopController() {
        return shopController;
    }

    // other methods
    public abstract void fillButtonsPanel(JPanel buttonsPanel);

    /**
     * do a JOptionPane.showMessageDialog() to show the error or problem
     * and add an error JLabel on the panel
     * @param message : the message in the panel
     * @param optionPaneMessage : the message in the JOptionPane.showMessageDialog()
     **/
    private void showErrorMessageAndPanel(String message, String optionPaneMessage) {
        JLabel errorLabel = new JLabel(message);
        this.add(errorLabel, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null,
                optionPaneMessage,
                "problème pour la recherche", JOptionPane.ERROR_MESSAGE);
    }

    // input validity tests
    private boolean isValideDouble (String textToVerify) {
        try {
            double reference = Double.parseDouble(textToVerify);
            return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                    "l'entrée de la tva et du niveau d'alcool doivent être un nombre (à virgule)",
                    "erreure d'entrée", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void showTextFieldInputError(String message, JTextField textField) {
        textField.setText(null);
        JOptionPane.showMessageDialog(null,
                message,
                "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isFormValid() {
        if (nameTextField.getText().length() > 180 || nameTextField.getText().length() == 0) {
            showTextFieldInputError("le nom doit contenir entre 0 et 180 caractères", nameTextField);
            return false;
        }

        if (referenceTextField.getText().length() > 10 || referenceTextField.getText().length() == 0) {
            showTextFieldInputError("la référence doit contenir entre 0 et 10 caractères", referenceTextField);
            return false;
        }

        if (productTypeComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,
                    "un type de produit doit être choisi",
                    "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!isValideDouble(vatTextField.getText()) || Double.parseDouble(vatTextField.getText()) > 100 || Double.parseDouble(vatTextField.getText()) < 0) {
            showTextFieldInputError("la tva doit être un nombre (à virgule ou non) entre 0 et 100", vatTextField);
            return false;
        }

        boolean alcoholLevelOrCheckBoxAreValid = !hasAlcoholCheckBox.isSelected() || ((isValideDouble(alcoholLevelTextField.getText()) && Double.parseDouble(alcoholLevelTextField.getText()) <= 100 && Double.parseDouble(alcoholLevelTextField.getText()) > 0));
        if (!alcoholLevelOrCheckBoxAreValid) {
            showTextFieldInputError("le taux d'alcool doit être un nombre (à virgule ou non) entre 0 et 100 % (100 compris mais 0 non), si le produit n'est pas alcoolisé, ne coché pas la case qui dit que le produit l'est", alcoholLevelTextField);
            return false;
        }

        if (!isValideDouble(priceTextField.getText()) || Double.parseDouble(priceTextField.getText()) < 0) {
            showTextFieldInputError("le prix doit être un nombre (à virgule ou non) non négatif", priceTextField);
            return false;
        }

        if ((int) quantityInStockSpinner.getValue() < 0) {
            JOptionPane.showMessageDialog(null,
                    "la quantité en stock doit être un nombre entier positif ou nul",
                    "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
            quantityInStockSpinner.setValue(0);
            return false;
        }

        if ((int) minimumQuantityInStockSpinner.getValue() < 0) {
                JOptionPane.showMessageDialog(null,
                        "la quantité minimum en stock doit être un nombre entier positif ou nul",
                        "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                minimumQuantityInStockSpinner.setValue(0);
                return false;
        }

        // every inputs are good
        return true;
    }

    /**
     * This readForm function will read each field one by one and check if the user wrote a valide value.
     * <br>
     * if it's not, a JOptionPane or a showTextFieldError() will show the mistake to the user and reset the field
    **/
    public Product readForm() {
        String name = nameTextField.getText();
        String reference = referenceTextField.getText();
        boolean hasADescription = descriptionTextField.getText().length() > 0;
        String description = hasADescription?descriptionTextField.getText():null;
        Double vat = Double.parseDouble(vatTextField.getText());
        Double alcoholLevel = hasAlcoholCheckBox.isSelected() ? Double.parseDouble(alcoholLevelTextField.getText()) : 0;
        Double price = Double.parseDouble(priceTextField.getText());
        int quantityInStock = (int) quantityInStockSpinner.getValue()    ;
        int minimumInStock = (int) minimumQuantityInStockSpinner.getValue();
        int productTypeReference = productTypeComboBox.getSelectedIndex() + 1;
        boolean isSparkling = isSparklingCheckBox.isSelected();
        LocalDate launchingDate = getLaunchingDateSelected().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Product product = new Product(reference, productTypeReference, name, vat, minimumInStock, isSparkling, launchingDate, price, alcoholLevel, quantityInStock, description);
        return product;
    }

    // listeners
    private class ChecboxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            boolean hasAlcohol = event.getStateChange() == ItemEvent.SELECTED;
            ProductCreatingAndModifingFormTemplate.this.getAlcoholLevelTextField().setEnabled(hasAlcohol);
        }
    }

    private class TextFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JTextField eventSource = (JTextField) event.getSource();

            String textFieldText = eventSource.getText();
            if(eventSource == nameTextField) {
                if (textFieldText.length() > 180 || textFieldText.length() == 0) {
                    showTextFieldInputError("le nom doit contenir entre 0 et 180 caractères", eventSource);
                }
            } else if(eventSource == referenceTextField) {
                if (textFieldText.length() > 10 || textFieldText.length() == 0) {
                    showTextFieldInputError("la référence doit contenir entre 0 et 10 caractères", eventSource);
                }
            } else if(eventSource == descriptionTextField) {
                if (textFieldText.length() < 1) {
                    eventSource.setText(null);
                }
            } else {
                if(eventSource == vatTextField) {
                    if (!isValideDouble(textFieldText) || Double.valueOf(textFieldText) > 100 || Double.valueOf(textFieldText) < 0) {
                        showTextFieldInputError("la tva doit être un nombre (à virgule ou non) entre 0 et 100", eventSource);
                    }
                } else if(eventSource == alcoholLevelTextField) {
                    if (!isValideDouble(textFieldText) || Double.valueOf(textFieldText) > 100 || Double.valueOf(textFieldText) <= 0) {
                        showTextFieldInputError("le taux d'alcool doit être un nombre (à virgule ou non) entre 0 et 100 % (100 compris mais 0 non), si le produit n'est pas alcoolisé, ne coché pas la case qui dit que le produit l'est", eventSource);
                    }
                } else if(eventSource == priceTextField) {
                    if (!isValideDouble(textFieldText) || Double.valueOf(textFieldText) < 0) {
                        showTextFieldInputError("le prix doit être un nombre (à virgule ou non) non négatif", eventSource);
                    }
                }
            }
        }
    }

    private class SpinnerListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent event) {
            JSpinner eventSource = (JSpinner) event.getSource();
            int spinnerValue = (int) eventSource.getValue();
            if(eventSource == quantityInStockSpinner) {
                if (spinnerValue < 0) {
                    JOptionPane.showMessageDialog(null,
                            "la quantité en stock doit être un nombre entier positif ou nul",
                            "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                    quantityInStockSpinner.setValue(0);
                }
            } else if(eventSource == minimumQuantityInStockSpinner) {
                if (spinnerValue < 0) {
                    JOptionPane.showMessageDialog(null,
                            "la quantité minimum en stock doit être un nombre entier positif ou nul",
                            "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                    minimumQuantityInStockSpinner.setValue(0);
                }
            }
        }
    }
}
