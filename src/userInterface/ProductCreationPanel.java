package userInterface;

import model.*;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.CreateDatasException;
import model.Exeptions.GetDatasException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductCreationPanel extends ProductCreatingAndModifingFormTemplate {

    public ProductCreationPanel() {
        super();
        getTitleLabel().setText("Vous pouvez remplir le formulaire pour créer le produit ici.");
    }

    @Override
    public void fillButtonsPanel(JPanel buttonsPanel) {
        JButton createButton = new JButton("Créer");
        createButton.addActionListener(new ButtonListener());
        buttonsPanel.add(createButton);
    }

    private void createProduct(Product productToCreate) {
        try {
            getShopController().createProduct(productToCreate);
            JOptionPane.showMessageDialog(null,
                    "L'objet a été créé et enregistré.",
                    "Création effectuée", JOptionPane.INFORMATION_MESSAGE);

        } catch (CreateConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur de conexion aux données", JOptionPane.ERROR_MESSAGE);

        } catch (CreateDatasException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Erreur de creation du produit", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isFormValid() {
        if(super.isFormValid()) {
            try {
                if (getShopController().productIdAlreayExist(getReferenceTextField().getText())) {
                    showTextFieldInputError("La référence est déjà utilisée par un autre produit." +
                            "\nVeuillez en choisir une autre.", getReferenceTextField());
                    return false;
                } else {
                    // every inputs are ok
                    return true;
                }

            } catch (CreateConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur de conexion aux données", JOptionPane.ERROR_MESSAGE);

            } catch (GetDatasException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Erreur de récupération des données de produits", JOptionPane.ERROR_MESSAGE);
            }
        }

        return false;
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductCreationPanel thisPanel = ProductCreationPanel.this;

            if (isFormValid()) {
                Product productToCreate = thisPanel.readForm();
                thisPanel.createProduct(productToCreate);

            } // isFormValid will show the errors
        }
    }
}
