package userInterface;

import model.*;

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
            if (isFormValid()) {

                try {
                    Product productToCreate = readForm();
                    // TODO créer
                    JOptionPane.showMessageDialog(null, "objet créé", "erreur", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Le formulaire doit être correctement rempli pour être validé", "formulaire mal remplis", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
