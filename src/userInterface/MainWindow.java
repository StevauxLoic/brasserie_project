package userInterface;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu, helpMenu, searchMenu, productMenu;
    JMenuItem exitMenuItem,
            userManuelMenuItem,
            softwareInfosMenuItem,
            searchProductMenuItem,
            searchAProductQuantityMenuItem,
            searchProductSupplementsDueToEventMenuItem,
            searchAdressMenuItem,
            searchOutOfStockProductMenuItem,
            newProductMenuItem,
            deleteProductMenuItem,
            modifyProductMenuItem,
            findProductMenuItem;
    JPanel welcomePanel;
    JLabel welcomeLabel;

    public MainWindow() throws HeadlessException {
        super("Application de gestion");
        setBounds(100, 100, 500, 500);

        menuBar = new JMenuBar();

        // Menus
        fileMenu = new JMenu("Fichier");
        helpMenu = new JMenu("Aide");
        searchMenu = new JMenu("Recherche");
        productMenu = new JMenu("Produit");

        // Menu items
        exitMenuItem = new JMenuItem("Quitter");
        fileMenu.add(exitMenuItem);

        userManuelMenuItem = new JMenuItem("Manuel d'utilisation");
        softwareInfosMenuItem = new JMenuItem("Infos sur l'application");
        helpMenu.add(userManuelMenuItem);
        helpMenu.add(softwareInfosMenuItem);

        searchProductMenuItem = new JMenuItem("Un Produit");
        searchAProductQuantityMenuItem = new JMenuItem("la quantité vendue d’un type de produit dans un délai");
        searchProductSupplementsDueToEventMenuItem = new JMenuItem("Des produits demandant un réassort supplémentaire");
        searchAdressMenuItem = new JMenuItem("Les adresses d’une personne/entreprise");
        searchOutOfStockProductMenuItem = new JMenuItem("Un produit en rupture dans le stock et ses fournisseurs");
        searchMenu.add(searchProductMenuItem);
        searchMenu.add(searchAProductQuantityMenuItem);
        searchMenu.add(searchProductSupplementsDueToEventMenuItem);
        searchMenu.add(searchAdressMenuItem);
        searchMenu.add(searchOutOfStockProductMenuItem);

        newProductMenuItem = new JMenuItem("Nouveau");
        deleteProductMenuItem = new JMenuItem("Suprimer");
        modifyProductMenuItem = new JMenuItem("Modifier/mettre à jour");
        findProductMenuItem = new JMenuItem("Trouver");
        productMenu.add(newProductMenuItem);
        productMenu.add(deleteProductMenuItem);
        productMenu.add(modifyProductMenuItem);
        productMenu.add(findProductMenuItem);

        // welcome
        welcomePanel = new JPanel(new FlowLayout());
        welcomeLabel = new JLabel("<html><p>Bienvenue sur le gestionnaire" +
                "</br>Le manuel d'utilisation se trouve dans le menu aide//Manuel d'utilisation</p></html>");
        welcomePanel.add(welcomeLabel);

        // window display
        this.setVisible(true);
    }
}
