package userInterface;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * this class is a panel where you can search a product then you can delete or modify it
 * <br>
 * <br>
 * it will first show the table of all products with 2 buttons under it (one to delete the product(s) and one to modify it)
 * <br>
 * if the button to delete is pushed, the selectedProduct is delete and if the modify button is selected, the panel will change
 * to a panel of modification for the selected porduct
*/
public class FindProductPanel extends JPanel {
    private JPanel titlePanel, tablePanel, buttonsPanel;
    private JLabel titleLabel;
    private JButton modifyButton, deleteButton;

    private JTable productsTable;
    private JScrollPane productsScrollPane;

    private ButtonListener buttonListener;
    private AllProductModel allProductModel;
    private ListSelectionModel listSelect;


    // TODO a supprimer quand on en aura plus besoin pour les tests
    private static Product testProduct, secondTestProduct;
    static {
        try {
            testProduct = new Product("productRef", 14,"productName test", 21, 20, false, LocalDate.of(2004, 11, 14), 15.5, 15.5, 12, "productDescritption test");
            secondTestProduct = new Product("txtRef", 2,"second test", 6, 5, true, LocalDate.of(2010, 9, 22), 4, 0, 11);
        } catch (Exception exeption) {
            System.out.println(exeption.getMessage());
        }
    }

    public FindProductPanel() {
        this.setLayout(new BorderLayout());

        //Panel
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        // JTables and scrollPane
        // TODO à supprimer la liste d'exemple
        ArrayList<Product> productsList = new ArrayList<Product>();
        productsList.add(testProduct);
        productsList.add(secondTestProduct);

        // if no table ==> the table and buttons are replace by JLabel that
        // explain the fact that there is not any product found

        if (!productsList.isEmpty()) {
            //@celian vien chck ici pour le JTable, le model est dans AllProductModel.java
            allProductModel = new AllProductModel(productsList);
            productsTable = new JTable(allProductModel);
            productsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            listSelect = productsTable.getSelectionModel();

            productsScrollPane = new JScrollPane(productsTable);
            tablePanel.add(productsTable);

            // button Listener
            buttonListener = new ButtonListener();

            // buttons
            deleteButton = new JButton("Suprimer");
            deleteButton.addActionListener(buttonListener);
            buttonsPanel.add(deleteButton);

            modifyButton = new JButton("Modifier");
            modifyButton.addActionListener(buttonListener);
            buttonsPanel.add(modifyButton);

            // add the button pannel

            this.add(buttonsPanel, BorderLayout.SOUTH);

        } else {
            tablePanel.add(new JLabel("pas de produits trouvé"));
        }

        // labels
        titleLabel = new JLabel("Table des rpoduits à sélectionner");
        titlePanel.add(titleLabel);

        // Panels filling
        // TODO fill the panel
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);

        this.setVisible(true);
    }


    private void displayModifyForm(Product selectedProduct) {
        this.removeAll();
        this.add(new ProductModifyingForm(selectedProduct));
        this.revalidate();
        this.repaint();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            FindProductPanel thisFindProductPanel = FindProductPanel.this;
            int firstSelectedRow = thisFindProductPanel.listSelect.getMinSelectionIndex();

            if (firstSelectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez d'abord séléctionner un/des produit(s) (un seul pour 'modifier')", "erreur de sélection", JOptionPane.WARNING_MESSAGE);
            } else {
                Object source = event.getSource();
                 if (source == deleteButton){
                    // TODO supression
                    JOptionPane.showMessageDialog(null, "Suppression du/des produit(s) ", "suppression produit(s)", JOptionPane.INFORMATION_MESSAGE);
                } else if (source == modifyButton) {
                    int lastSelected = thisFindProductPanel.listSelect.getMaxSelectionIndex();

                    if (lastSelected != firstSelectedRow) {
                        JOptionPane.showMessageDialog(null, "Veuillez un seul élément doit être séléctionné pour être modifié", "erreur de sélection", JOptionPane.WARNING_MESSAGE);
                    } else {
                        FindProductPanel.this.displayModifyForm(thisFindProductPanel.allProductModel.getObject(firstSelectedRow));
                    }
                }
            }
        }
    }
}