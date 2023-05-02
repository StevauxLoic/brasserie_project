package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu, helpMenu, searchMenu, productMenu;
    JMenuItem exitMenuItem,
            userManuelMenuItem,
            softwareInfosMenuItem,
            searchProductMenuItem,
            searchProductTypeOnDelayAMenuItem,
            searchProductSupplementsDueToEventMenuItem,
            searchAdressMenuItem,
            searchOutOfStockProductMenuItem,
            newProductMenuItem,
            findProductMenuItem;

    JPanel welcomePanel, activePanel;
    JLabel welcomeLabel;

    public MainWindow() throws HeadlessException {
        // window
        super("Application de gestion");
        setSize(500, 500);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.addWindowListener( new WindowAdapter() {
            public void windowClosing ( WindowEvent closingWindowEvent ) {
                System.exit(0);
            }
        });

        // Menu bar
        menuBar = new JMenuBar();
        this.add(menuBar, BorderLayout.NORTH);

        // Menus
        fileMenu = new JMenu("Fichier");
        helpMenu = new JMenu("Aide");
        searchMenu = new JMenu("Recherche");
        productMenu = new JMenu("Produit");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(searchMenu);
        menuBar.add(productMenu);

        // Menu items
        MenuItemListener menuItemListener = new MenuItemListener();
        exitMenuItem = new JMenuItem("Quitter");
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(menuItemListener);

        userManuelMenuItem = new JMenuItem("Manuel d'utilisation");
        userManuelMenuItem.addActionListener(menuItemListener);
        softwareInfosMenuItem = new JMenuItem("Infos sur l'application");
        softwareInfosMenuItem.addActionListener(menuItemListener);
        helpMenu.add(userManuelMenuItem);
        helpMenu.add(softwareInfosMenuItem);

        searchProductMenuItem = new JMenuItem("Un Produit");
        searchProductMenuItem.addActionListener(menuItemListener);
        searchProductTypeOnDelayAMenuItem = new JMenuItem("La quantité vendue d’un type de produit dans un délai");
        searchProductTypeOnDelayAMenuItem.addActionListener(menuItemListener);
        searchProductSupplementsDueToEventMenuItem = new JMenuItem("Des produits demandant un réassort supplémentaire");
        searchProductSupplementsDueToEventMenuItem.addActionListener(menuItemListener);
        searchAdressMenuItem = new JMenuItem("Les adresses d’une personne/entreprise");
        searchAdressMenuItem.addActionListener(menuItemListener);
        searchOutOfStockProductMenuItem = new JMenuItem("Un produit en rupture dans le stock et ses fournisseurs");
        searchOutOfStockProductMenuItem.addActionListener(menuItemListener);
        searchMenu.add(searchProductMenuItem);
        searchMenu.add(searchProductTypeOnDelayAMenuItem);
        searchMenu.add(searchProductSupplementsDueToEventMenuItem);
        searchMenu.add(searchAdressMenuItem);
        searchMenu.add(searchOutOfStockProductMenuItem);


        newProductMenuItem = new JMenuItem("Nouveau");
        newProductMenuItem.addActionListener(menuItemListener);
        findProductMenuItem = new JMenuItem("Trouver/modifier/supprimer");
        findProductMenuItem.addActionListener(menuItemListener);
        productMenu.add(newProductMenuItem);
        productMenu.add(findProductMenuItem);

        // welcome
        welcomePanel = new JPanel(new FlowLayout());
        welcomePanel.setLayout(new FlowLayout());

        welcomeLabel = new JLabel("<html><p style=\"text-align: center;\"><image src=\"https://pngimg.com/d/welcome_PNG81.png\" alt=\"l'image n'a put charger\" width=\"220\" height=\"100\"><br>Bienvenue sur le gestionnaire" +
                "<br>Le manuel d'utilisation se trouve dans le menu aide/Manuel d'utilisation</p></html>");
        welcomePanel.add(welcomeLabel);

        this.add(welcomePanel, BorderLayout.CENTER);

        //set active panel
        activePanel = welcomePanel;

        // window display
        this.setVisible(true);

    }

    public void setActivePanel(JPanel activePanel) {
        this.activePanel = activePanel;
    }

    private void setPanelToDisplay(JPanel pannelToDisplay) {
        this.remove(activePanel);
        this.add(pannelToDisplay, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private class MenuItemListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source == exitMenuItem) {
                ExitTheAppPopUp exitTheAppPopUp = new ExitTheAppPopUp();
            } else if(source == userManuelMenuItem) {
                UserManuelPopUp userManuelPopUp = new UserManuelPopUp();
            } else if(source == softwareInfosMenuItem) {
                SoftwareInfosPopUp softwareInfosPopUp = new SoftwareInfosPopUp();
            } else {
                MainWindow thisMainWindow = MainWindow.this;
                JPanel panelToDisplay = null;
                if(source == searchProductMenuItem || source == findProductMenuItem) { // les deux mènenet au même endroit (une recherche de produit)
                    panelToDisplay = new FindProductPanel();
                } else if(source == searchProductTypeOnDelayAMenuItem) {
                    panelToDisplay = new ProductTypeSearchOnDelayPanel();
                } else if(source == searchProductSupplementsDueToEventMenuItem) {
                    panelToDisplay = new ProductSupplementDueToEventSearchPanel();
                } else if(source == searchAdressMenuItem) {
                    panelToDisplay = new AdressSearchPanel();
                } else if(source == searchOutOfStockProductMenuItem) {
                    panelToDisplay = new ProductOutOfStockAndSupplierSearchPanel();
                } else if(source == newProductMenuItem) {
                    panelToDisplay = new ProductCreationPanel();
                }
                thisMainWindow.setPanelToDisplay(panelToDisplay);
                thisMainWindow.setActivePanel(panelToDisplay);
            }
        }
    }
}
