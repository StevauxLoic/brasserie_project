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

    public MainWindow() throws HeadlessException {
        super("Application de gestion");
        setBounds(100, 100, 500, 500);
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();

        fileMenu = new JMenu("fichier");
        helpMenu = new JMenu("aide");
        searchMenu = new JMenu("recherche");
        productMenu = new JMenu("produit");


    }
}
