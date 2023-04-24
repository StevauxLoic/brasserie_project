package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDeletingPanel extends ProductSearchForm {

    private Product foundProduct;

    @Override
    public void buttonsPanelUnderProductInfos(Product foundProduct, JPanel buttonsPanel) {
        if (foundProduct != null) {
            JButton deleteButton = new JButton("Suprimer");
            deleteButton.addActionListener(new ButtonListener());
            this.foundProduct = foundProduct;

            buttonsPanel.add(deleteButton);
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // TODO page de supression
            JOptionPane.showMessageDialog(null, "Suppression du produit " + foundProduct.getName(), "suppression de produit", JOptionPane.WARNING_MESSAGE);
        }
    }
}