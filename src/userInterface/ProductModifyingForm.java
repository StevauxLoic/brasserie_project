package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductModifyingForm extends ProductCreatingAndModifingForm{

    private JButton modifyButton;
    private Product productToModify;

    public ProductModifyingForm(Product productToModify) {
        super();
        this.productToModify = productToModify;
    }

    @Override
    public void fillButtonsPanel(JPanel buttonsPanel) {
        modifyButton = new JButton("modifier");
        modifyButton.addActionListener(new ButtonListener());
        buttonsPanel.add(modifyButton);
    }

    private void modifyProduct(Product productToModify, Product modifiedProduct) {
        // TODO updating the product in the database
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
