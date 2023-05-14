package userInterface;

import Controller.ShopController;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.SelectException;
import model.ProductSoldInADelay;
import model.ProductType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProductSoldOnDelaySearchPanel extends JPanel {

    private ShopController shopController;

    private JComboBox productTypeComboBox;
    private JSpinner delayBeginingSpinner,
                        delayEndSpinner;
    private JLabel titleLabel,
                    productTypeLabel,
                    delayBeginingLabel,
                    delayEndLabel;
    private JPanel titlePanel,
                    formPanel,
                    buttonsPanel;
    private JButton searchButton;
    private Date delayBeginingDateSelected,
                    delayEndDateSelected;

    private ArrayList<ProductType> productTypesList;

    public ProductSoldOnDelaySearchPanel() {
        this.setLayout(new BorderLayout());

        this.shopController = new ShopController();

        try {
            // panels
            titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout());

            formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(3, 2));

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());

            // listener
            ButtonListener buttonListener = new ButtonListener();

            // modules
            titleLabel = new JLabel("Recherche de produit vendu dans un certain délai");
            titlePanel.add(titleLabel);

            productTypeLabel = new JLabel("type de produit");
            productTypesList = shopController.getAllProductType();
            String[] productTypesNamesList = new String[productTypesList.size()];
            for (int iProductType = 0; iProductType < productTypesList.size(); iProductType++) {
                productTypesNamesList[iProductType] = productTypesList.get(iProductType).getLabel()
                        + '(' + productTypesList.get(iProductType).getReference();
            }
            productTypeComboBox = new JComboBox(productTypesNamesList);
            formPanel.add(productTypeLabel);
            formPanel.add(productTypeComboBox);

            delayBeginingLabel = new JLabel("date de début de la recherche");
            delayBeginingDateSelected = new Date();
            delayBeginingSpinner = new JSpinner(new SpinnerDateModel(delayBeginingDateSelected, null, null, Calendar.YEAR));
            JSpinner.DateEditor delayBeginingEditor = new JSpinner.DateEditor(delayBeginingSpinner, "dd/MM/yyyy");
            delayBeginingSpinner.setEditor(delayBeginingEditor);
            formPanel.add(delayBeginingLabel);
            formPanel.add(delayBeginingSpinner);

            delayEndLabel = new JLabel("date de fin de la recherche");
            delayEndSpinner = new JSpinner();
            delayEndDateSelected = new Date();
            delayEndSpinner = new JSpinner(new SpinnerDateModel(delayEndDateSelected, null, null, Calendar.YEAR));
            JSpinner.DateEditor delayEndEditor = new JSpinner.DateEditor(delayEndSpinner, "dd/MM/yyyy");
            delayEndSpinner.setEditor(delayEndEditor);
            formPanel.add(delayEndLabel);
            formPanel.add(delayEndSpinner);


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

    public LocalDate getDelayBeginingDate() {
        return ((Date) delayBeginingSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getDelayEndDate() {
        return ((Date) delayEndSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean isFormValid() {
        if (productTypeComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "un type d'objet doit être sélectionné", "erreur de type de produit", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        LocalDate delaybeginingDate = getDelayBeginingDate();
        LocalDate endingDate = getDelayEndDate();

        if (delaybeginingDate.equals(endingDate)) {
            JOptionPane.showMessageDialog(null, "les deux dates (début et fin) ne peuvent être identique", "erreur de dates", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (delaybeginingDate.isAfter(endingDate)) {
            JOptionPane.showMessageDialog(null, "la date de début doit être avant la date de fin", "erreur de dates", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void search() {
        if (isFormValid()) {
            try {
                ArrayList<ProductSoldInADelay> productTypesSoldList = shopController.getAllProductSoldInADelay(
                                                    getDelayBeginingDate(), getDelayEndDate(),
                                                    productTypesList.get(productTypeComboBox.getSelectedIndex()));

                if (!productTypesSoldList.isEmpty()) {
                    this.removeAll();

                    AllProductsSoldInADelayModel allProductSoldInADelayModel = new AllProductsSoldInADelayModel(productTypesSoldList);
                    JTable productsTable = new JTable(allProductSoldInADelayModel);
                    productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    JScrollPane productTypesScrollPane = new JScrollPane(productsTable);

                    this.add(productTypesScrollPane, BorderLayout.CENTER);
                } else {
                    JOptionPane.showMessageDialog(null, "aucunes vente n'a été trouvée dans ce délai", "aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);
                }
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
        }
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

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductSoldOnDelaySearchPanel.this.search();
        }
    }

}
