package userInterface;

import controller.ShopController;
import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;
import model.Exeptions.GetDatasException;
import userInterface.searchs.BusinessEntityAdressSearchPanel;
import userInterface.searchs.ProductOutOfStockSearchPanel;
import userInterface.searchs.ProductSoldOnDelaySearchPanel;
import userInterface.searchs.ProductSupplementDueToEventSearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu, searchMenu, productMenu;
    private JMenuItem exitMenuItem,
            userManuelMenuItem,
            softwareInfosMenuItem,
            searchProductMenuItem,
            searchProductTypeOnDelayAMenuItem,
            searchProductSupplementsDueToEventMenuItem,
            searchAdressMenuItem,
            searchOutOfStockProductMenuItem,
            newProductMenuItem,
            findProductMenuItem;

    private JPanel activePanel;
    private WelcomePanel welcomePanel;

    public MainWindow() throws HeadlessException {

        // window

        super("Application de gestion");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // listeners

        this.addWindowListener( new WindowAdapter() {
            public void windowClosing ( WindowEvent closingWindowEvent ) {
                try {
                    ShopController shopController = new ShopController();
                    shopController.closeConnection();
                } catch (CreateConnectionException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(),
                            "Erreur de conexion aux données", JOptionPane.ERROR_MESSAGE);
                } catch (CloseConnectionException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(),
                            "Erreur de la fermeture de la conexion", JOptionPane.ERROR_MESSAGE);
                }

                System.exit(0);
            }
        });

        MenuItemListener menuItemListener = new MenuItemListener();

        // Menu bar

        menuBar = new JMenuBar();
        this.add(menuBar, BorderLayout.NORTH);

        // Menus

        fileMenu = new JMenu("Fichier");
        menuBar.add(fileMenu);

        helpMenu = new JMenu("Aide");
        menuBar.add(helpMenu);

        searchMenu = new JMenu("Recherche");
        menuBar.add(searchMenu);

        productMenu = new JMenu("Produit");
        menuBar.add(productMenu);

        // Menu items

        exitMenuItem = new JMenuItem("Quitter");
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(menuItemListener);

        userManuelMenuItem = new JMenuItem("Manuel d'utilisation");
        userManuelMenuItem.addActionListener(menuItemListener);
        helpMenu.add(userManuelMenuItem);

        softwareInfosMenuItem = new JMenuItem("Infos sur l'application");
        softwareInfosMenuItem.addActionListener(menuItemListener);
        helpMenu.add(softwareInfosMenuItem);

        searchProductMenuItem = new JMenuItem("Un Produit");
        searchProductMenuItem.addActionListener(menuItemListener);
        searchMenu.add(searchProductMenuItem);

        searchProductTypeOnDelayAMenuItem = new JMenuItem("La quantité vendue d’un type de produit dans un délai");
        searchProductTypeOnDelayAMenuItem.addActionListener(menuItemListener);
        searchMenu.add(searchProductTypeOnDelayAMenuItem);

        searchProductSupplementsDueToEventMenuItem = new JMenuItem("Des produits demandant un réassort supplémentaire");
        searchProductSupplementsDueToEventMenuItem.addActionListener(menuItemListener);
        searchMenu.add(searchProductSupplementsDueToEventMenuItem);

        searchAdressMenuItem = new JMenuItem("Les adresses d’une personne/entreprise");
        searchAdressMenuItem.addActionListener(menuItemListener);
        searchMenu.add(searchAdressMenuItem);

        searchOutOfStockProductMenuItem = new JMenuItem("Un produit en rupture dans le stock et ses fournisseurs");
        searchOutOfStockProductMenuItem.addActionListener(menuItemListener);
        searchMenu.add(searchOutOfStockProductMenuItem);

        newProductMenuItem = new JMenuItem("Nouveau");
        newProductMenuItem.addActionListener(menuItemListener);
        productMenu.add(newProductMenuItem);

        findProductMenuItem = new JMenuItem("Trouver/modifier/supprimer");
        findProductMenuItem.addActionListener(menuItemListener);
        productMenu.add(findProductMenuItem);

        // welcome

        welcomePanel = new WelcomePanel();
        this.add(welcomePanel, BorderLayout.CENTER);

        //set active panel

        activePanel = welcomePanel;

        // window display

        this.setVisible(true);
    }

    public void setActivePanel(JPanel activePanel) {
        if (this.activePanel == welcomePanel) {
            welcomePanel.stopBottlesAnimation();
        }
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
                    panelToDisplay = new ProductSoldOnDelaySearchPanel();
                } else if(source == searchProductSupplementsDueToEventMenuItem) {
                    panelToDisplay = new ProductSupplementDueToEventSearchPanel();
                } else if(source == searchAdressMenuItem) {
                    panelToDisplay = new BusinessEntityAdressSearchPanel();
                } else if(source == searchOutOfStockProductMenuItem) {
                    panelToDisplay = new ProductOutOfStockSearchPanel();
                } else if(source == newProductMenuItem) {
                    panelToDisplay = new ProductCreationPanel();
                }
                thisMainWindow.setPanelToDisplay(panelToDisplay);
                thisMainWindow.setActivePanel(panelToDisplay);
            }
        }
    }
}
