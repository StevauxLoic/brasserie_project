package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductCreationPanel extends ProductCreatingAndModifingForm {

    private JButton creatButton;

    //TODO c'est utile ce constructeur ? car il est là mais peut être useless
    public ProductCreationPanel() {
        super();
    }

    @Override
    public void fillButtonsPanel(JPanel buttonsPanel) {
        this.creatButton = new JButton("créer");
        creatButton.addActionListener(new ButtonListener());
        buttonsPanel.add(creatButton);
    }

    private void createProduct(Product productToCreate) {
        // TODO create product in the data base
        // si erreur JOptionPane
        JOptionPane.showMessageDialog(null, "création de l'objet en db", "création de produit", JOptionPane.INFORMATION_MESSAGE);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Product productToCreate = readForm();

            if (productToCreate != null) {
                createProduct(productToCreate);
            }
        }
    }
}
