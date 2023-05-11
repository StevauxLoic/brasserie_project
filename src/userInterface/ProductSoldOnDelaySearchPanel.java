package userInterface;

import model.ProductSoldInADelay;

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

    public ProductSoldOnDelaySearchPanel() {
        this.setLayout(new BorderLayout());

        // panels
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3,2));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        // listener
        ButtonListener buttonListener = new ButtonListener();

        // modules
        titleLabel = new JLabel("Recherche de produit vendu dans un certain délai");
        titlePanel.add(titleLabel);


        productTypeLabel = new JLabel("type de produit");
        // TODO get the types list
        productTypeComboBox = new JComboBox(new String[]{"spiritueu", "bière", "soda", "whisky"});
        productTypeComboBox.setSelectedItem(null);
        formPanel.add(productTypeLabel);
        formPanel.add(productTypeComboBox);

        delayBeginingLabel = new JLabel("date de début de la recherche");
        delayBeginingDateSelected = new Date();
        delayBeginingSpinner = new JSpinner(new SpinnerDateModel(delayBeginingDateSelected, null,null, Calendar.YEAR));
        JSpinner.DateEditor delayBeginingEditor = new JSpinner.DateEditor(delayBeginingSpinner,"dd/MM/yyyy");
        delayBeginingSpinner.setEditor(delayBeginingEditor);
        formPanel.add(delayBeginingLabel);
        formPanel.add(delayBeginingSpinner);

        delayEndLabel = new JLabel("date de fin de la recherche");
        delayEndSpinner = new JSpinner();
        delayEndDateSelected = new Date();
        delayEndSpinner = new JSpinner(new SpinnerDateModel(delayEndDateSelected, null, null, Calendar.YEAR));
        JSpinner.DateEditor delayEndEditor = new JSpinner.DateEditor(delayEndSpinner,"dd/MM/yyyy");
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
            // TODO recherche et afficher dans le JTable
            ArrayList<ProductSoldInADelay> productTypesSoldList = new ArrayList<ProductSoldInADelay>();
            if (!productTypesSoldList.isEmpty()) {
                this.removeAll();

                AllProductsSoldInADelayModel allProductSoldInADelayModel = new AllProductsSoldInADelayModel(productTypesSoldList);
                JTable productsTable = new JTable(allProductSoldInADelayModel);
                productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                ListSelectionModel listSelect = productsTable.getSelectionModel();

                JScrollPane productTypesScrollPane = new JScrollPane(productsTable);

                this.add(productTypesScrollPane, BorderLayout.CENTER);
            } else {
                JOptionPane.showMessageDialog(null, "aucunes vente n'a été trouvée dans ce délai", "aucune donnée trouvée", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ProductSoldOnDelaySearchPanel.this.search();
        }
    }

}
