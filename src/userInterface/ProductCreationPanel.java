package userInterface;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.CreateDatasException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductCreationPanel extends ProductCreatingAndModifingFormTemplate {

    private JButton createButton;

    public ProductCreationPanel() {
        super();
    }

    @Override
    public void fillButtonsPanel(JPanel buttonsPanel) {
        this.createButton = new JButton("créer");
        createButton.addActionListener(new ButtonListener());
        buttonsPanel.add(createButton);
    }

    private void createProduct(Product productToCreate) {
        try {
            getShopController().createProduct(productToCreate);
            JOptionPane.showMessageDialog(null,
                    "l'objet a été créé",
                    "création effectuée", JOptionPane.INFORMATION_MESSAGE);

        } catch (CreateConnectionException exception) {
            JOptionPane.showMessageDialog(null,
                    "erreur : " + exception.getMessage(),
                    "erreur de creation de la connexion au données", JOptionPane.ERROR_MESSAGE);

        } catch (CreateDatasException exception) {
            JOptionPane.showMessageDialog(null,
                    "erreur : " + exception.getMessage(),
                    "erreur de creation du produit", JOptionPane.ERROR_MESSAGE);

        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductCreationPanel thisPanel =ProductCreationPanel.this;

            if (isFormValid()) {
                Product productToCreate = thisPanel.readForm();
                thisPanel.createProduct(productToCreate);

            } else {
                JOptionPane.showMessageDialog(null,
                        "Le formulaire doit être correctement rempli pour être validé",
                        "formulaire mal remplis", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
