package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.nio.file.Path;

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
        setSize(500, 500);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

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
        exitMenuItem = new JMenuItem("Quitter");
        fileMenu.add(exitMenuItem);

        userManuelMenuItem = new JMenuItem("Manuel d'utilisation");
        softwareInfosMenuItem = new JMenuItem("Infos sur l'application");
        helpMenu.add(userManuelMenuItem);
        helpMenu.add(softwareInfosMenuItem);

        searchProductMenuItem = new JMenuItem("Un Produit");
        searchAProductQuantityMenuItem = new JMenuItem("La quantité vendue d’un type de produit dans un délai");
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
        modifyProductMenuItem = new JMenuItem("Modifier / mettre à jour");
        findProductMenuItem = new JMenuItem("Trouver");
        productMenu.add(newProductMenuItem);
        productMenu.add(deleteProductMenuItem);
        productMenu.add(modifyProductMenuItem);
        productMenu.add(findProductMenuItem);

        // welcome
        welcomePanel = new JPanel(new FlowLayout());
        welcomePanel.setLayout(new FlowLayout());

        welcomeLabel = new JLabel("<html><p style=\"text-align: center;\"><image src=\"https://pngimg.com/d/welcome_PNG81.png\" alt=\"l'image n'a put charger\" width=\"220\" height=\"100\"><br>Bienvenue sur le gestionnaire" +
                "<br>Le manuel d'utilisation se trouve dans le menu aide/Manuel d'utilisation</p></html>");
        welcomePanel.add(welcomeLabel);

        this.add(welcomePanel, BorderLayout.CENTER);

        // window display
        this.setVisible(true);
    }

    private class MenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if(source = exitMenuItem) {...}
                    else if(source = userManuelMenuItem) {...}
                    else if(source = softwareInfosMenuItem) {...}

                    else if(source = searchProductMenuItem) {...}
                    else if(source = searchAProductQuantityMenuItem) {...}
                    else if(source = searchProductSupplementsDueToEventMenuItem) {...}
                    else if(source = searchAdressMenuItem) {...}
                    else if(source = searchOutOfStockProductMenuItem) {...}

                    else if(source = newProductMenuItem) {...}
                    else if(source = deleteProductMenuItem) {...}
                    else if(source = modifyProductMenuItem) {...}
                    else if(source = findProductMenuItem) {...}
        }
    }
}
