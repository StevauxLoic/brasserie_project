package userInterface.searchs;

import controller.ShopController;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import model.ProductSupplementDueToEvent;
import userInterface.tableModels.AllProductSupplementsDueToEventModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProductSupplementDueToEventSearchPanel extends JPanel {

    private ShopController shopController;

    private JLabel titleLabel,
                    delayBeginingLabel,
                    delayEndLabel;
    private JPanel titlePanel,
                    formPanel,
                    buttonsPanel;
    private JButton searchButton;
    private Date delayBeginingDateSelected,
                    delayEndDateSelected;
    private JSpinner delayBeginingSpinner,
                    delayEndSpinner;

    public ProductSupplementDueToEventSearchPanel() {
        this.setLayout(new BorderLayout());

        this.shopController = new ShopController();

        // panels
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout( 2, 2, 10, 10));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        // listener
        ButtonListener buttonListener = new ButtonListener();

        // modules
        titleLabel = new JLabel("<html><p>Recherche des produits avec une demande de réassort supplémentaire" +
                                    "<br>suite aux évènements dans un délai donné." +
                                    "<br>Remplissez le formulaire pour cliquer sur le bouton et effectuer la recherche." +
                                    "</p></html>");
        titlePanel.add(titleLabel);


        delayBeginingLabel = new JLabel("Date de début de la recherche : ");
        delayBeginingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        delayBeginingDateSelected = new Date();
        delayBeginingSpinner = new JSpinner(new SpinnerDateModel(delayBeginingDateSelected, null,null, Calendar.YEAR));
        JSpinner.DateEditor delayBeginingEditor = new JSpinner.DateEditor(delayBeginingSpinner,"dd/MM/yyyy");
        delayBeginingSpinner.setEditor(delayBeginingEditor);
        formPanel.add(delayBeginingLabel);
        formPanel.add(delayBeginingSpinner);

        delayEndLabel = new JLabel("Date de fin de la recherche : ");
        delayEndLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        delayEndSpinner = new JSpinner();
        delayEndDateSelected = new Date();
        delayEndSpinner = new JSpinner(new SpinnerDateModel(delayEndDateSelected, null, null, Calendar.YEAR));
        JSpinner.DateEditor delayEndEditor = new JSpinner.DateEditor(delayEndSpinner,"dd/MM/yyyy");
        delayEndSpinner.setEditor(delayEndEditor);
        formPanel.add(delayEndLabel);
        formPanel.add(delayEndSpinner);


        searchButton = new JButton("Chercher");
        searchButton.addActionListener(buttonListener);
        buttonsPanel.add(searchButton);

        // fill the panel and display it
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public LocalDate getDelayBeginingDate() {
        return ((Date) delayBeginingSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getDelayEndDate() {
        return ((Date) delayEndSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean isFormValid() {
        LocalDate delaybeginingDate = getDelayBeginingDate();
        LocalDate endingDate = getDelayEndDate();

        if (delaybeginingDate.equals(endingDate)) {
            JOptionPane.showMessageDialog(null,
                    "Les deux dates (début et fin) ne peuvent être identiques.",
                    "Erreur de dates", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (delaybeginingDate.isAfter(endingDate)) {
            JOptionPane.showMessageDialog(null,
                    "La date de début doit être avant la date de fin.",
                    "Erreur de dates", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void search() {
        if (isFormValid()) {
            try {
                ArrayList<ProductSupplementDueToEvent> foundProductSupplementDueToEventList = shopController
                                    .getAllProductSupplementDueToEvent(getDelayBeginingDate(), getDelayEndDate());

                if (!foundProductSupplementDueToEventList.isEmpty()) {
                    this.removeAll();

                    AllProductSupplementsDueToEventModel allProductSupplementDueToEvent =
                            new AllProductSupplementsDueToEventModel(foundProductSupplementDueToEventList);
                    JTable productsTable = new JTable(allProductSupplementDueToEvent);
                    productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    JScrollPane productsScrollPane = new JScrollPane(productsTable);

                    this.add(productsScrollPane, BorderLayout.CENTER);

                    // refresh panel
                    this.repaint();
                    this.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Aucuns produit lié à un évènement dans ce délai n'a été trouvé.",
                            "Aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (GetDatasException exception) {
                JOptionPane.showMessageDialog(null,
                        "Une érreur est survenue lors de la recherche des données sur les suppléments sûts à un évènement.",
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
            ProductSupplementDueToEventSearchPanel.this.search();
        }
    }

}
