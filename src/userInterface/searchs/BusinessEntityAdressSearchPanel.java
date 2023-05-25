package userInterface.searchs;

import controller.ShopController;
import model.BusinessEntity;
import model.BusinessEntityAdress;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import userInterface.tableModels.AllBusinessesEntityAdressModel;

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
            formPanel.setLayout(new GridLayout(1,2, 10, 10));

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());

            // listener
            ButtonListener buttonListener = new ButtonListener();

            // modules
            titleLabel = new JLabel("Recherche des adresses d’une personne/entreprise");
            titlePanel.add(titleLabel);


            businessEntityLabel = new JLabel("Personne/entreprise : ");
            businessEntityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
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


            searchButton = new JButton("Chercher");
            searchButton.addActionListener(buttonListener);
            buttonsPanel.add(searchButton);

            // fill the panel and display it
            this.add(titlePanel, BorderLayout.NORTH);
            this.add(formPanel, BorderLayout.CENTER);
            this.add(buttonsPanel, BorderLayout.SOUTH);
        } catch (GetDatasException exception) {
            showErrorMessageAndPanel("<html><p>La recherche des types de produits n'a pas été possible," +
                            "<br>les types de produits sont nécéssaire pour modifier ou créer un produit." +
                            "<br>Veuillez réessayer en recliquant sur le menus ou redémarrant l'application." +
                            "<br>Message d'erreur : " + exception.getMessage() + "</p></html>",
                    "erreur : " + exception.getMessage());
        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>La connection aux données n'a pas pu être établie." +
                            "<br>Veuillez réessayer en recliquant sur le menus ou redémarrant l'application." +
                            "<br>Message d'erreur : " + exception.getMessage() + "</p></html>",
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
        JOptionPane.showMessageDialog(null, optionPaneMessage, "Problème pour la recherche", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isFormValid() {
        if (businessEntityComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Une personne/entreprise doit être sélectinée " +
                            "pour pouvoir rechercher ses adresses.",
                    "Erreur de type de produit", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void search() {
        if (isFormValid()) {
            ArrayList<BusinessEntityAdress> adresseslist;
            try {
                BusinessEntity selectedBusinessEntity = businessEntitiesList.get(businessEntityComboBox.getSelectedIndex());
                adresseslist = shopController.getAllAdressesOfABusinessEntity(selectedBusinessEntity);
                if (!adresseslist.isEmpty()) {
                    formPanel.removeAll();
                    buttonsPanel.removeAll();

                    AllBusinessesEntityAdressModel allBusinessEntityAdressModel = new AllBusinessesEntityAdressModel(adresseslist);
                    JTable adressesTable = new JTable(allBusinessEntityAdressModel);
                    adressesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    JScrollPane adressesScrollPane = new JScrollPane(adressesTable);

                    formPanel.add(adressesScrollPane, BorderLayout.CENTER);

                    titleLabel.setText("Voici toutes les adresses enregistrées de " + selectedBusinessEntity.getName() + '.');

                    this.repaint();
                    this.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune adresse n'a été trouvée. " +
                                    "Aucune n'a été enregistrée pour cette personne/entrepriseL",
                            "Aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (GetDatasException exception) {
                JOptionPane.showMessageDialog(null,
                        "Une érreur est survenue lors de la recherche des données sur les adresses.",
                        "Erreur de recherche", JOptionPane.ERROR_MESSAGE);

            } catch (CreateConnectionException exception) {
                JOptionPane.showMessageDialog(null,
                        "Une érreur est survenue lors de la création d'une connection aux données.",
                        "Erreur de connexion aux données", JOptionPane.ERROR_MESSAGE);
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
