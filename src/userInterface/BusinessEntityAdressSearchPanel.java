package userInterface;

import Controller.ShopController;
import model.BusinessEntity;
import model.BusinessEntityAdress;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BusinessEntityAdressSearchPanel extends JPanel {
    private JComboBox businessEntityComboBox;
    private JLabel titleLabel,
                    businessEntityLabel;
    private JPanel titlePanel,
                    formPanel,
                    buttonsPanel;
    private JButton searchButton;

    private ArrayList<BusinessEntity> businessEntitiesList;

    private ShopController shopController;


    public BusinessEntityAdressSearchPanel() {
        this.setLayout(new BorderLayout());

        this.shopController = new ShopController();

        // try if the access to the businessEntities work,
        // if not display the error in the panel and a JOPtionPane.showMessageDialog()
        // by using the method showErrorMessageAndPanel()
        try {
            // panels
            titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout());

            formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(1,2));

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());

            // listener
            ButtonListener buttonListener = new ButtonListener();

            // modules
            titleLabel = new JLabel("Recherche des adresses d’une personne/entreprise");
            titlePanel.add(titleLabel);


            businessEntityLabel = new JLabel("personne/entreprise");
            businessEntitiesList = shopController.getAllBusinessEntities();
            // create an array that contains the names and reference (in a String) for the JComboBox
            String [] businessEntitiesNamesList = new String[businessEntitiesList.size()];
            for (int iBusinessEntity = 0; iBusinessEntity < businessEntitiesList.size(); iBusinessEntity++) {
                businessEntitiesNamesList[iBusinessEntity] = businessEntitiesList.get(iBusinessEntity).getName()
                                                            + "(ref:"
                                                            + businessEntitiesList.get(iBusinessEntity).getReference()
                                                            + ')';
            }
            businessEntityComboBox = new JComboBox(businessEntitiesNamesList);
            businessEntityComboBox.setSelectedItem(null);
            formPanel.add(businessEntityLabel);
            formPanel.add(businessEntityComboBox);


            searchButton = new JButton("chercher");
            searchButton.addActionListener(buttonListener);
            buttonsPanel.add(searchButton);

            // fill the panel and display it
            this.add(titlePanel, BorderLayout.NORTH);
            this.add(formPanel, BorderLayout.CENTER);
            this.add(buttonsPanel, BorderLayout.SOUTH);
        } catch (SelectException exception) {
            showErrorMessageAndPanel("<html><p>la recherche des types de produits n'a pas été possible," +
                            "<br>les types de produits sont nécéssaire pour modifier ou créer un produit" +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>erreur : " + exception.getMessage() + "</p></html>",
                    "erreur : " + exception.getMessage());
        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>la connection aux données n'a pas pu être établie" +
                            "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>erreur : " + exception.getMessage()+ "</p></html>",
                    "erreur : " + exception.getMessage());
        }

        this.setVisible(true);
    }


    /**
     * do a JOptionPane.showMessageDialog() to show the error or problem
     * and add an error JLabel on the panel
     * @param message : the message in the panel
     * @param optionPaneMessage : the message in the JOptionPane.showMessageDialog()
     **/
    private void showErrorMessageAndPanel(String message, String optionPaneMessage) {
        JLabel errorLabel = new JLabel(message);
        this.add(errorLabel, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, optionPaneMessage, "problème pour la recherche", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isFormValid() {
        if (businessEntityComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "une personne/entreprise doit être sélectinée", "erreur de type de produit", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void search() {
        if (isFormValid()) {
            ArrayList<BusinessEntityAdress> adressesLsit = null;
            try {
                adressesLsit = shopController.getAllAdressesOfABusinessEntity(businessEntitiesList
                                                .get(businessEntityComboBox.getSelectedIndex()));
                if (!adressesLsit.isEmpty()) {
                    this.removeAll();

                    AllBusinessesEntityAdressModel allBusinessEntityAdressModel = new AllBusinessesEntityAdressModel(adressesLsit);
                    JTable adressesTable = new JTable(allBusinessEntityAdressModel);
                    adressesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    JScrollPane adressesScrollPane = new JScrollPane(adressesTable);

                    this.add(adressesScrollPane, BorderLayout.CENTER);
                } else {
                    JOptionPane.showMessageDialog(null, "aucunes adresse n'a été trouvée",
                            "aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (SelectException exception) {
                JOptionPane.showMessageDialog(null, "erreur : " + exception.getMessage(),
                        "erreur de recherche", JOptionPane.ERROR_MESSAGE);

            } catch (CreateConnectionException exception) {
                JOptionPane.showMessageDialog(null, "erreur : " + exception.getMessage(),
                        "erreur de connexion aux données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            BusinessEntityAdressSearchPanel.this.search();
        }
    }

}
