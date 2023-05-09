package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UserManuelPopUp extends PopUp {

    private JPanel infosSelecterContainer, infosContainer;
    private JLabel comboBoxLabel,
            helpInfosLabel,
            softwareInfoLabel,
            menusLabel,
            fileMenuLabe,
            helpMenuLabe,
            searchMenuLabe,
            productMenuLabe,
            searchProductLabel,
            searchProductByQuantityLabel,
            searchProductSupplementDueToEventLabel,
            searchAdressLabel,
            searchProductOutOfStockLabel,
            newProductLabel,
            findProductLabel;

    private JComboBox infoListComboBox;

    private Integer displayedLabelIndex;

    private static String[] infosTopics = {"Infos sur l'application", "menus", "menu fichier", "menu aide", "menu recherche",
            "menu produit", "menu rechercher/Rechercher un produit",
            "menu rechercher/Rechercher la quantité d’un type de produit dans un délai donné",
            "menu rechercher/Recherche des produits avec une demande de réassort supplémentaire suite aux évènements dans un délai donné",
            "menu rechercher/Recherche les adresses d’une personne/entreprise",
            "menu rechercher/Rechercher un produit en rupture dans le stock et ses fournisseurs",
            "menu produit/Nouveau produit", "menu produit/Trouver produit"};

    private JLabel[] labelsArray;

    public UserManuelPopUp() {
        super("information de l'application", 1000, 500);
     }

    @Override
    public void fillThePopUpFrame() {
        this.setLayout(new BorderLayout());

        // panels

        infosSelecterContainer = new JPanel();
        infosContainer = new JPanel();
        infosContainer.setLayout(new FlowLayout());

        // the left part of the screen with the ComboBox to choose the topic we want to see more infos onto

        comboBoxLabel = new JLabel("Aide à afficher :");
        infoListComboBox = new JComboBox(infosTopics);
        infoListComboBox.setSelectedItem(null);

        JComboBoxListener jComboBoxListener = new JComboBoxListener();
        infoListComboBox.addItemListener(jComboBoxListener);

        infosSelecterContainer.add(comboBoxLabel, BorderLayout.NORTH);
        infosSelecterContainer.add(infoListComboBox, BorderLayout.SOUTH);

        // the label that appear when the window appears :

        helpInfosLabel = new JLabel("<html><p>Selectionnez un thème sur lequel vous voulez être informé</p></html>");
        infosContainer.add(helpInfosLabel);

        // the diffrents label that contain infos on a topic :

        softwareInfoLabel = new JLabel("<html><p>infos de l'app</p></html>");

        menusLabel = new JLabel("<html><p>infos des menus</p></html>");

        fileMenuLabe = new JLabel("<html><p>infos menu fichier</p></html>");

        helpMenuLabe = new JLabel("<html><p>infos menu iade</p></html>");

        searchMenuLabe = new JLabel("<html><p>infos menu recherche</p></html>");

        productMenuLabe = new JLabel("<html><p>infos menu produit</p></html>");

        searchProductLabel = new JLabel("<html><p>infos rechercher/produit</p></html>");

        searchProductByQuantityLabel = new JLabel("<html><p>infos rechercher/produit par quantité</p></html>");

        searchProductSupplementDueToEventLabel = new JLabel("<html><p>infos rechercher/produit supp du à un evnet</p></html>");

        searchAdressLabel = new JLabel("<html><p>infos rechercher/adresse</p></html>");

        searchProductOutOfStockLabel = new JLabel("<html><p>infos rechercher/ produit hors stock</p></html>");

        newProductLabel = new JLabel("<html><p>infos nouveau produit</p></html>");

        findProductLabel = new JLabel("<html><p>infos trouver produit</p></html>");


        // fill the array of help messages
        labelsArray = new JLabel[13];
        labelsArray[0] = softwareInfoLabel;
        labelsArray[1] = menusLabel;
        labelsArray[2] = fileMenuLabe;
        labelsArray[3] = helpMenuLabe;
        labelsArray[4] = searchMenuLabe;
        labelsArray[5] = productMenuLabe;
        labelsArray[6] = searchProductLabel;
        labelsArray[7] = searchProductByQuantityLabel;
        labelsArray[8] = searchProductSupplementDueToEventLabel;
        labelsArray[9] = searchAdressLabel;
        labelsArray[10] = searchProductOutOfStockLabel;
        labelsArray[11] = newProductLabel;
        labelsArray[12] = findProductLabel;

        // add the components to the container
        this.add(infosSelecterContainer, BorderLayout.NORTH);
        this.add(infosContainer, BorderLayout.CENTER);
    }

    private void changeDisplayedLabel (int labelIndex) {
        infosContainer.removeAll();
        infosContainer.add(labelsArray[labelIndex]);
        infosContainer.repaint();
        infosContainer.revalidate();
        displayedLabelIndex = labelIndex;
    }

    private class JComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            int selectedTopic = infoListComboBox.getSelectedIndex();
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Integer selctedTopicIndex = UserManuelPopUp.this.displayedLabelIndex;
                if (selctedTopicIndex == null || selectedTopic != selctedTopicIndex){
                    changeDisplayedLabel(selectedTopic);
                }
            }
        }
    }
}
