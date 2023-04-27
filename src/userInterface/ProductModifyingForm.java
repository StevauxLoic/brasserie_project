package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductModifyingForm extends ProductCreatingAndModifingForm{

    private JButton modifyButton;
    private Product productToModify;
    
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

    public ProductModifyingForm(Product productToModify) {
        super();
        this.productToModify = productToModify;

        this.isSparklingCheckBox = this.getIsSparklingCheckBox();
        isSparklingCheckBox.setSelected(productToModify.isSparkling());

        this.hasAlcoholCheckBox = this.getHasAlcoholCheckBox();
        hasAlcoholCheckBox.setSelected(productToModify.getAlcoholLevel() > 0);

        this.productTypeComboBox = this.getProductTypeComboBox();
        productTypeComboBox.setSelectedIndex(productToModify.getTypeReference());

        this.nameTextField = this.getNameTextField();
        nameTextField.setText(productToModify.getName());

        this.referenceTextField = this.getReferenceTextField();
        referenceTextField.setText(productToModify.getReference());

        this.vatTextField = this.getVatTextField();
        vatTextField.setText(String.valueOf(productToModify.getVat()));

        this.quantityInStockTextField = this.getQuantityInStockTextField();
        quantityInStockTextField.setText(String.valueOf(productToModify.getQuantityInStock()));

        this.minimumQuantityInStockTextField = this.getMinimumQuantityInStockTextField();
        minimumQuantityInStockTextField.setText(String.valueOf(productToModify.getMinimumQuantityInStock()));

        this.alcoholLevelTextField = this.getAlcoholLevelTextField();
        alcoholLevelTextField.setText(String.valueOf(productToModify.getAlcoholLevel()));

        this.priceTextField = this.getPriceTextField();
        priceTextField.setText(String.valueOf(productToModify.getPrice()));

        this.descriptionTextField = this.getDescriptionTextField();
        descriptionTextField.setText(productToModify.getDescription());

        this.launchingDateSpinner = this.getLaunchingDateSpinner();
        launchingDateSpinner.setValue(productToModify.getLaunchingDate());

        if (hasAlcoholCheckBox.isSelected()) {
            alcoholLevelTextField.setEnabled(false);
        }
    }


    @Override
    public void fillButtonsPanel(JPanel buttonsPanel) {
        modifyButton = new JButton("modifier");
        modifyButton.addActionListener(new ButtonListener());
        buttonsPanel.add(modifyButton);
    }

    private void modifyProduct(Product productToModify, Product modifiedProduct) {
        // TODO updating the product in the database
        // si erreur JOptionPane
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Product modifiedProduct = readForm();

            if (modifiedProduct != null) {
                modifyProduct(productToModify, modifiedProduct);
            }
        }
    }

}
