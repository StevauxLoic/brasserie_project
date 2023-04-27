package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductModifyingPanel extends ProductSearchForm {

    private Product foundProduct;

    @Override
    public void buttonsPanelUnderProductInfos(Product foundProduct, JPanel buttonsPanel) {
        if (foundProduct != null) {
            JButton modifyButton = new JButton("Modifier");
            modifyButton.addActionListener(new ButtonListener());
            this.foundProduct = foundProduct;

            buttonsPanel.add(modifyButton);
        }
    }

    private void displayModifyingForm() {
        this.removeAll();
        this.add(new ProductModifyingForm(foundProduct));
        this.revalidate();
        this.repaint();
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductModifyingPanel.this.displayModifyingForm();
        }
    }
}
