package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UserManuelPopUp extends PopUp {

    private Container infosSelecterContainer, infosContainer;
    private JLabel infoListLabel,
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
            deleteProductLabel,
            modifyProductLabel,
            findProductLabel;

    private JComboBox infoListComboBox;

    private Integer displayedLabelIndex;

    private static String[] infosTopics = {"Infos sur l'application", "menus", "menu fichier", "menu aide", "menu recherche",
            "menu recherche", "menu produit", "menu rechercher/Rechercher un produit",
            "menu rechercher/Rechercher la quantité d’un type de produit dans un délai donné",
            "menu rechercher/Recherche des produits avec une demande de réassort supplémentaire suite aux évènements dans un délai donné",
            "menu rechercher/Recherche les adresses d’une personne/entreprise",
            "menu rechercher/Rechercher un produit en rupture dans le stock et ses fournisseurs",
            "menu produit/Nouveau produit", "menu produit/Suprimer produit", "menu produit/Modifier produit",
            "menu produit/Trouver produit"};

    private JLabel[] labelsArray;

    public UserManuelPopUp() {
        super("information de l'application");
     }

    @Override
    public void fillTheContainer() {
        this.setLayout(new BorderLayout());

        infosSelecterContainer = new Container();
        infosContainer = new Container();
        infosContainer.setLayout(new FlowLayout());

        // the left part of the screen with the ComboBox to choose the topic we want to see more infos onto
        infoListLabel = new JLabel("Aide à afficher :");
        infoListComboBox = new JComboBox(infosTopics);
        JComboBoxListener jComboBoxListener = new JComboBoxListener();
        infoListComboBox.addItemListener(jComboBoxListener);

        infosSelecterContainer.add(infoListLabel);
        infosSelecterContainer.add(infoListComboBox);

        // the label that appear when the window appears :
        helpInfosLabel = new JLabel("<html><p>Selectionnez un thème sur lequel vous voulez être informé</p></html>");
        infosContainer.add(helpInfosLabel);

        // the diffrents label that contain infos on a topic :
        softwareInfoLabel = new JLabel("<html></html>");

        menusLabel = new JLabel("<html></html>");

        fileMenuLabe = new JLabel("<html></html>");

        helpMenuLabe = new JLabel("<html></html>");

        searchMenuLabe = new JLabel("<html></html>");

        productMenuLabe = new JLabel("<html></html>");

        searchProductLabel = new JLabel("<html></html>");

        searchProductByQuantityLabel = new JLabel("<html></html>");

        searchProductSupplementDueToEventLabel = new JLabel("<html></html>");

        searchAdressLabel = new JLabel("<html></html>");

        searchProductOutOfStockLabel = new JLabel("<html></html>");

        newProductLabel = new JLabel("<html></html>");

        deleteProductLabel = new JLabel("<html></html>");

        modifyProductLabel = new JLabel("<html></html>");

        findProductLabel = new JLabel("<html></html>");


        labelsArray = new JLabel[17];
        labelsArray[0] = infoListLabel;
        labelsArray[1] = helpInfosLabel;
        labelsArray[2] = softwareInfoLabel;
        labelsArray[3] = menusLabel;
        labelsArray[4] = fileMenuLabe;
        labelsArray[5] = helpMenuLabe;
        labelsArray[6] = searchMenuLabe;
        labelsArray[7] = productMenuLabe;
        labelsArray[8] = searchProductLabel;
        labelsArray[9] = searchProductByQuantityLabel;
        labelsArray[10] = searchProductSupplementDueToEventLabel;
        labelsArray[11] = searchAdressLabel;
        labelsArray[12] = searchProductOutOfStockLabel;
        labelsArray[13] = newProductLabel;
        labelsArray[14] = deleteProductLabel;
        labelsArray[15] = modifyProductLabel;
        labelsArray[16] = findProductLabel;

        // add the components to the container
        mainContainer.add(infosSelecterContainer, BorderLayout.WEST);
        mainContainer.add(infosContainer, BorderLayout.CENTER);
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
                if (selctedTopicIndex != null && selectedTopic != selctedTopicIndex){
                    changeDisplayedLabel(selectedTopic);
                }
            }
        }
    }
}
