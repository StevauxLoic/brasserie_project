package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSearchPanel extends ProductSearchForm {

    private Product foundProduct;

    @Override
    public void buttonsPanelUnderProductInfos(Product foundProduct, JPanel buttonsPanel) {
        if (foundProduct != null) {
            JButton showReferenceButton = new JButton("Afficher la référence dans un pop-up");
            showReferenceButton.addActionListener(new ButtonListener());
            this.foundProduct = foundProduct;

            buttonsPanel.add(showReferenceButton);
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(null, ProductSearchPanel.this.foundProduct.getReference(), "Référence de" + ProductSearchPanel.this.foundProduct.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
