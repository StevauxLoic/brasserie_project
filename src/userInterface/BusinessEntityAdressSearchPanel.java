package userInterface;

import model.BusinessEntityAdress;

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


    public BusinessEntityAdressSearchPanel() {
        this.setLayout(new BorderLayout());

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
        // TODO get the types list
        businessEntityComboBox = new JComboBox(new String[]{"Mike", "Célain & Co.", "Mardi gras enterprise", "solidaris forever"});
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

        this.setVisible(true);
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
            // TODO recherche et afficher dans le JTable
            ArrayList<BusinessEntityAdress> adressesLsit = new ArrayList<BusinessEntityAdress>();
            if (!adressesLsit.isEmpty()) {
                this.removeAll();

                AllBusinessesEntityAdressModel allBusinessEntityAdressModel = new AllBusinessesEntityAdressModel(adressesLsit);
                JTable adressesTable = new JTable(allBusinessEntityAdressModel);
                adressesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                ListSelectionModel listSelect = adressesTable.getSelectionModel();

                JScrollPane adressesScrollPane = new JScrollPane(adressesTable);

                this.add(adressesScrollPane, BorderLayout.CENTER);
            } else {
                JOptionPane.showMessageDialog(null, "aucunes adresse n'a été trouvée", "aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);
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
