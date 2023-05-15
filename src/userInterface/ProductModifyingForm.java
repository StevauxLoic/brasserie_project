package userInterface;

import model.Exeptions.CreateConnectionException;
import model.Exeptions.UpdateException;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ProductModifyingForm extends ProductCreatingAndModifingFormTemplate {

    private JButton modifyButton;
    private Product productToModify;
    
    private JCheckBox isSparklingCheckBox,
                        hasAlcoholCheckBox;
    private JComboBox productTypeComboBox;
    private JTextField nameTextField,
                        referenceTextField,
                        vatTextField,
                        alcoholLevelTextField,
                        priceTextField,
                        descriptionTextField;
    private JSpinner quantityInStockSpinner,
                        minimumQuantityInStockSpinner;

    private JSpinner launchingDateSpinner;

    public ProductModifyingForm(Product productToModify) {
        super();
        this.productToModify = productToModify;

        // get and fill the different input and field with the productToModify informations
        if(getProductTypeComboBox() != null) {
            fillForm();
        }
    }

    public void fillForm() {
        this.isSparklingCheckBox = this.getIsSparklingCheckBox();
        isSparklingCheckBox.setSelected(productToModify.isSparkling());

        this.hasAlcoholCheckBox = this.getHasAlcoholCheckBox();
        hasAlcoholCheckBox.setSelected(productToModify.getAlcoholLevel() > 0);

        this.productTypeComboBox = this.getProductTypeComboBox();
        productTypeComboBox.setSelectedIndex(productToModify.getTypeReference() - 1);

        this.nameTextField = this.getNameTextField();
        nameTextField.setText(productToModify.getName());

        this.referenceTextField = this.getReferenceTextField();
        referenceTextField.setText(productToModify.getReference());
        referenceTextField.setEnabled(false);

        this.vatTextField = this.getVatTextField();
        vatTextField.setText(String.valueOf(productToModify.getVat()));

        this.quantityInStockSpinner = this.getQuantityInStockSpinner();
        quantityInStockSpinner.setValue(productToModify.getQuantityInStock());

        this.minimumQuantityInStockSpinner = this.getMinimumQuantityInStockSpinner();
        minimumQuantityInStockSpinner.setValue(productToModify.getMinimumQuantityInStock());

        this.alcoholLevelTextField = this.getAlcoholLevelTextField();
        alcoholLevelTextField.setText(String.valueOf(productToModify.getAlcoholLevel()));
        // if checkBox is Selected the field shhould be enabeled
        alcoholLevelTextField.setEnabled(hasAlcoholCheckBox.isSelected());

        this.priceTextField = this.getPriceTextField();
        priceTextField.setText(String.valueOf(productToModify.getPrice()));

        this.descriptionTextField = this.getDescriptionTextField();
        descriptionTextField.setText(productToModify.getDescription());

        this.launchingDateSpinner = this.getLaunchingDateSpinner();
        LocalDate launchingDateToSet = productToModify.getLaunchingDate();
        launchingDateSpinner.setValue(java.sql.Date.valueOf(launchingDateToSet));
    }

    @Override
    public void fillButtonsPanel(JPanel buttonsPanel) {
        modifyButton = new JButton("modifier");
        modifyButton.addActionListener(new ButtonListener());
        buttonsPanel.add(modifyButton);
    }

    private void modifyProduct(Product modifiedProduct) {
        try {
            getShopController().updateProduct(modifiedProduct);
            JOptionPane.showMessageDialog(null, "objet modifié",
                    "produit modifié avec succès", JOptionPane.INFORMATION_MESSAGE);

        } catch (UpdateException exception) {
            JOptionPane.showMessageDialog(null, "erreur : " + exception.getMessage(),
                    "erreur de la modification du produit", JOptionPane.ERROR_MESSAGE);

        } catch (CreateConnectionException exception) {
            JOptionPane.showMessageDialog(null, "erreur : " + exception.getMessage(),
                    "erreur de creation de la connexion au données", JOptionPane.ERROR_MESSAGE);

        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductModifyingForm thisPanel = ProductModifyingForm.this;
            if (thisPanel.isFormValid()) {
                Product modifiedProduct = thisPanel.readForm();
                thisPanel.modifyProduct(modifiedProduct);
            } else {
                JOptionPane.showMessageDialog(null, "Le formulaire doit être correctement rempli pour être validé", "formulaire mal remplis", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
