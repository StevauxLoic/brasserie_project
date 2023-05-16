package userInterface.searchs;

import controller.ShopController;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import model.ProductSoldInADelay;
import model.ProductType;
import userInterface.tableModels.AllProductsSoldInADelayModel;

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
            formPanel.setLayout(new GridLayout(3, 2, 10, 10));

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout());

            // listener
            ButtonListener buttonListener = new ButtonListener();

            // modules
            titleLabel = new JLabel("<html><p>Recherche de produit vendu dans un intervale donné." +
                    "<br>Remplissez le formulaire puis cliquez sur le bouton pour faire la recherche.</p></html>");
            titlePanel.add(titleLabel);

            productTypeLabel = new JLabel("Type de produit : ");
            productTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            productTypesList = shopController.getAllProductType();
            String[] productTypesNamesList = new String[productTypesList.size()];
            for (int iProductType = 0; iProductType < productTypesList.size(); iProductType++) {
                productTypesNamesList[iProductType] = productTypesList.get(iProductType).getLabel()
                                                        + "(ref:"
                                                        + productTypesList.get(iProductType).getReference()
                                                        + ')';
            }
            productTypeComboBox = new JComboBox(productTypesNamesList);
            formPanel.add(productTypeLabel);
            formPanel.add(productTypeComboBox);

            delayBeginingLabel = new JLabel("Date de début de la recherche : ");
            delayBeginingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            delayBeginingDateSelected = new Date();
            delayBeginingSpinner = new JSpinner(new SpinnerDateModel(delayBeginingDateSelected, null, null, Calendar.YEAR));
            JSpinner.DateEditor delayBeginingEditor = new JSpinner.DateEditor(delayBeginingSpinner, "dd/MM/yyyy");
            delayBeginingSpinner.setEditor(delayBeginingEditor);
            formPanel.add(delayBeginingLabel);
            formPanel.add(delayBeginingSpinner);

            delayEndLabel = new JLabel("Date de fin de la recherche : ");
            delayEndLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            delayEndSpinner = new JSpinner();
            delayEndDateSelected = new Date();
            delayEndSpinner = new JSpinner(new SpinnerDateModel(delayEndDateSelected, null, null, Calendar.YEAR));
            JSpinner.DateEditor delayEndEditor = new JSpinner.DateEditor(delayEndSpinner, "dd/MM/yyyy");
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
        } catch (GetDatasException exception) {
            showErrorMessageAndPanel("<html><p>La recherche des types de produits n'a pas été possible," +
                            "<br>les types de produits sont nécéssaire pour modifier ou créer un produit." +
                            "<br>Veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>Message d'erreur : " + exception.getMessage() + "</p></html>",
                    "Erreur : " + exception.getMessage());
        } catch (CreateConnectionException exception) {
            showErrorMessageAndPanel("<html><p>La connection aux données n'a pas pu être établie." +
                            "<br>Veuillez réessayer en recliquant sur le menus ou redémarrant l'application" +
                            "<br>Message d'rreur : " + exception.getMessage()+ "</p></html>",
                    "Erreur : " + exception.getMessage());
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
            JOptionPane.showMessageDialog(null,
                    "Un type d'objet doit être sélectionné pour éffectuer la recherche.",
                    "Erreur de type de produit", JOptionPane.WARNING_MESSAGE);
            return false;
        }

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

                    // refresh panel
                    this.repaint();
                    this.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Aucune vente n'a été trouvée dans ce délai.",
                            "Aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (GetDatasException exception) {
                showErrorMessageAndPanel("<html><p>La recherche des types de produits n'a pas été possible," +
                                "<br>les types de produits sont nécéssaires pour modifier ou créer un produit." +
                                "<br>Veuillez réessayer en recliquant sur le menus ou redémarrant l'application." +
                                "<br>Message d'erreur : " + exception.getMessage() + "</p></html>",
                        "Erreur : " + exception.getMessage());
            } catch (CreateConnectionException exception) {
                showErrorMessageAndPanel("<html><p>La connection aux données n'a pas pu être établie." +
                                "<br>veuillez réessayer en recliquant sur le menus ou redémarrant l'application." +
                                "<br>Message d'erreur : " + exception.getMessage()+ "</p></html>",
                        "Erreur : " + exception.getMessage());
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
        JOptionPane.showMessageDialog(null, optionPaneMessage,
                "Problème pour la recherche", JOptionPane.ERROR_MESSAGE);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductSoldOnDelaySearchPanel.this.search();
        }
    }

}
