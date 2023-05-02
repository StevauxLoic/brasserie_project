package userInterface;

import model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.sql.Date;

/**
 * This abstract class is for the creat and modifying part of the crud (on the table Product)
 * <br> <br>
 * it will create a whole form with fields, panel, chckboxes,... and have a function to read the form
 * <br>
 * the fields have a listener that check if the value is valid, if it is not, the user will be notified and the value reset
**/
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
                        alcoholLevelTextField,
                        priceTextField,
                        descriptionTextField;
    // int spinners
    private JSpinner quantityInStockSpinner,
            minimumQuantityInStockSpinner;
    //date spinner
    private JSpinner launchingDateSpinner;

    private Date launchingDateSelected;

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
        alcoholLevelTextField = new JTextField();
        priceTextField = new JTextField();
        descriptionTextField = new JTextField();
        
        TextFieldListener textFieldListener = new TextFieldListener();
        nameTextField.addActionListener(textFieldListener);
        referenceTextField.addActionListener(textFieldListener);
        vatTextField.addActionListener(textFieldListener);
        alcoholLevelTextField.addActionListener(textFieldListener);
        priceTextField.addActionListener(textFieldListener);
        descriptionTextField.addActionListener(textFieldListener);

        // JComboBox
        productTypeComboBox = new JComboBox(new String[]{"spiritueu", "bière", "soda", "whisky"});
        // TODO get the types list

        // spinners
        quantityInStockSpinner = new JSpinner();
        minimumQuantityInStockSpinner = new JSpinner();

        launchingDateSelected = new Date(1, 1, 0); // default var we decided to use TODO decide about it
        launchingDateSpinner = new JSpinner(new SpinnerDateModel(launchingDateSelected, null, null, Calendar.YEAR));
        JSpinner.DateEditor launchingDateEditor = new JSpinner.DateEditor(launchingDateSpinner,"dd/MM/yyyy");
        launchingDateSpinner.setEditor(launchingDateEditor);
        add(launchingDateSpinner);

        SpinnerListener spinnerListener = new SpinnerListener();
        quantityInStockSpinner.addChangeListener(spinnerListener);
        minimumQuantityInStockSpinner.addChangeListener(spinnerListener);



        // fill the panel and display it
        fillFormPanel(formPanel);
        fillButtonsPanel(buttonsPanel);

        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

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
        formPanel.add(quantityInStockSpinner);

        formPanel.add(minimumQuantityInStockLabel);
        formPanel.add(minimumQuantityInStockSpinner);

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
        return launchingDateSelected;
    }

    //TODO verif que la valeur est la même avant et après le parsint ou le parsdouble
    private boolean isValideDouble (String textToVerify) {
        try {
            double reference = Double.parseDouble(textToVerify);
            return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "l'entrée de la tva et du niveau d'alcool doivent être un nombre (à virgule ou non)", "erreure d'entrée", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    private void showTextFieldError(String message, JTextField textField) {
        textField.setText(null);
        JOptionPane.showMessageDialog(null, message, "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This readForm function will read each field one by one and check if the user wrote a valide value.
     * <br>
     * if it's not, a JOptionPane or a showTextFieldError() will show the mistake to the user and reset the field
    **/
    public Product readForm() {
        // lis les champs et renvoie le produit en fonction de et renvoie null si au moins un champs est mal rempli
        // chaque champs est lu, si il est validé il est enregistré dans une variable sinon la fonction renvoie directement null
        // si tout les champs sont validés un Product avec les données lues sera renvoyé
        if (nameTextField.getText().length() > 180 || nameTextField.getText().length() == 0) {
            showTextFieldError("le nom doit contenir entre 0 et 180 caractères", nameTextField);
        } else {
            String name = nameTextField.getText();

            if (referenceTextField.getText().length() > 10 || referenceTextField.getText().length() == 0) {
                showTextFieldError("la référence doit contenir entre 0 et 10 caractères", referenceTextField);
            } else {
                String reference = referenceTextField.getText();

                // en cas d'erreur pour ne pas avoir de "" à la place de null
                if (descriptionTextField.getText().length() < 1) {
                    descriptionTextField.setText(null);
                }
                String description = descriptionTextField.getText();

                if (!isValideDouble(vatTextField.getText()) || Double.parseDouble(vatTextField.getText()) > 100 || Double.parseDouble(vatTextField.getText()) < 0) {
                    showTextFieldError("la tva doit être un nombre (à virgule ou non) entre 0 et 100", vatTextField);
                } else {
                    Double vat = Double.parseDouble(vatTextField.getText());

                    boolean alcoholLevelOrCheckBoxAreValid = !hasAlcoholCheckBox.isSelected() || ((isValideDouble(alcoholLevelTextField.getText()) && Double.parseDouble(alcoholLevelTextField.getText()) <= 100 && Double.parseDouble(alcoholLevelTextField.getText()) > 0));
                    if (!alcoholLevelOrCheckBoxAreValid) {
                        showTextFieldError("le taux d'alcool doit être un nombre (à virgule ou non) entre 0 et 100 % (100 compris mais 0 non), si le produit n'est pas alcoolisé, ne coché pas la case qui dit que le produit l'est", alcoholLevelTextField);
                    } else {
                        Double alcoholLevel = hasAlcoholCheckBox.isSelected() ? Double.parseDouble(alcoholLevelTextField.getText()) : 0;

                        if (!isValideDouble(priceTextField.getText()) || Double.parseDouble(priceTextField.getText()) < 0) {
                            showTextFieldError("le prix doit être un nombre (à virgule ou non) non négatif", priceTextField);
                        } else {
                            Double price = Double.parseDouble(priceTextField.getText());

                            if ((int) quantityInStockSpinner.getValue() < 0) {
                                JOptionPane.showMessageDialog(null, "la quantité en stock doit être un nombre entier positif ou nul", "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                                quantityInStockSpinner.setValue(0);
                            } else {
                                int quantityInStock = (int) quantityInStockSpinner.getValue()    ;

                                if ((int) minimumQuantityInStockSpinner.getValue() < 0) {
                                    JOptionPane.showMessageDialog(null, "la quantité minimum en stock doit être un nombre entier positif ou nul", "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                                    minimumQuantityInStockSpinner.setValue(0);
                                } else {
                                    int minimumInStock = (int) minimumQuantityInStockSpinner.getValue();

                                    if (productTypeComboBox.getSelectedItem() == null) {
                                        JOptionPane.showMessageDialog(null, "un type de produit doit être choisi", "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        int productTypeReference = productTypeComboBox.getSelectedIndex() + 1;
                                        boolean isSparkling = isSparklingCheckBox.isSelected();

                                        try {
                                            Product product = new Product(reference, productTypeReference, name, vat, minimumInStock, isSparkling, getLaunchingDateSelected().toLocalDate(), price, alcoholLevel, description, quantityInStock);
                                            return product;
                                        } catch (Exception exception) {
                                                JOptionPane.showMessageDialog(null, exception.getMessage(), "erreurs rencontrée", JOptionPane.WARNING_MESSAGE);
                                        }
                                        return null;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
                    JOptionPane.showMessageDialog(null, "la quantité en stock doit être un nombre entier positif ou nul", "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                    quantityInStockSpinner.setValue(0);
                }
            } else if(eventSource == minimumQuantityInStockSpinner) {
                if (spinnerValue < 0) {
                    JOptionPane.showMessageDialog(null, "la quantité minimum en stock doit être un nombre entier positif ou nul", "erreur d'entrée", JOptionPane.WARNING_MESSAGE);
                    minimumQuantityInStockSpinner.setValue(0);
                }
            }
        }
    }
}
