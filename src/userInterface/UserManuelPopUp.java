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

    private static String[] infosTopics = {
            "Infos sur l'application",
            "Menus",
            "Menu fichier",
            "Menu aide",
            "menu recherche",
            "Menu produit",
            "Menu rechercher/Rechercher un produit",
            "Menu rechercher/Rechercher la quantité d’un type de produit dans un délai donné",
            "Menu rechercher/Recherche des produits avec une demande de réassort supplémentaire suite aux évènements dans un délai donné",
            "Menu rechercher/Recherche les adresses d’une personne/entreprise",
            "Menu rechercher/Rechercher un produit en rupture dans le stock et ses fournisseurs",
            "Menu produit/Nouveau",
            "Menu produit/Trouver-modifier-suprimer"
    };

    private JLabel[] labelsArray;

    public UserManuelPopUp() {
        super("Information de l'application", 1000, 500);
     }

    @Override
    public void fillThePopUpFrame() {
        this.setLayout(new BorderLayout());

        // panels

        infosSelecterContainer = new JPanel();
        infosSelecterContainer.setLayout(new FlowLayout());
        infosContainer = new JPanel();
        infosContainer.setLayout(new FlowLayout());

        // the left part of the screen with the ComboBox to choose the topic we want to see more infos onto

        comboBoxLabel = new JLabel("Aide à afficher : ");
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

        softwareInfoLabel = new JLabel("<html><p>" +
                                            "Cette application sert à aider un magasin de boisson dans sa gestion" +
                                            "</p></html>");

        menusLabel = new JLabel("<html><p>" +
                                    "En haut de la fenêtre se trouvent différent 'menus'" +
                                    "<br>Ceux-ci ous permetent d'accéder aux différentes fonctionnalité de l'application" +
                                    "<br>chque menu représente un thème de fonctionnalité " +
                                    "<br>par exemple le menu 'recherche' contient différents types de recherches" +
                                    "<br>il y en a 4 :</p>" +
                                    "<ol><li>Fichier</li>" +
                                    "<li>Aide</li>" +
                                    "<li>Recherche</li>" +
                                    "<li>Produit</li>" +
                                    "</ol><p>chaque menu contient des sous-menus que vous pouvez selectionner pour acceder" +
                                    "<br>à une fonctionnalité de l'application" +
                                    "</p></html>");

        fileMenuLabe = new JLabel("<html><p>" +
                                        "Le menu fichier contient uniquement le sous-menu 'Quitter'" +
                                        "<br>Ce sous menu sert à fermer l'application" +
                                        "</p></html>");

        helpMenuLabe = new JLabel("<html><p>" +
                                        "Le menu aide contient 2 sous-menus</p>" +
                                        "<ol><li>manuel d'utilisation</li>" +
                                        "<li>infos sur l'application</li>" +
                                        "</ol><p>Ce premier sous-menu donne des informations sur l'utilisation de l'application" +
                                        "<br>il s'agit du sous-menu menant à cette page" +
                                        "<br>Le sous-menu suivant donne des informations mineurs sur l'application" +
                                        "<br>vous pouvez y trouver le bt de cette application aisin que ses développeurs" +
                                        "</p></html>");

        searchMenuLabe = new JLabel("<html><p>" +
                                        "Le menu de recherche contient différentes recherches possibles" +
                                        "<br>il en existe 5 et ont des buts biens différents" +
                                        "<ol><li>Produit</li>" +
                                        "<li>Quantité d’un type de produit dans un délai</li>" +
                                        "<li>Des produits demandant un réassort supplémentaire</li>n" +
                                        "<li>Les adresses d'une personne/entreprise</li>" +
                                        "<li>Un produit en rupture dans le stock et ses fournisseurs</li>" +
                                        "</ol><p>exepté pour le premier sous menu, chacun vous amènent à un formulaire" +
                                        "<br>ce formulaire une fois rempli permet de faire une recherche" +
                                        "<br>Le premier sous-menu (Produit) affiche directement une liste au lieu d'un formulaire" +
                                        "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                        "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                        "<br>qu'elle n'a pas put charger" +
                                        "</p></html>");

        productMenuLabe = new JLabel("<html><p>" +
                                            "Le menu Produit contient différentes actions liées aux produits" +
                                            "<br>il en existe 2 mais la deuxième permet plus de possibilités" +
                                            "<ol><li>Nouveau</li>" +
                                            "<li>Trouver/Modifier/supprimer</li>" +
                                            "</ol><p>le premier vous affiche un formulaire qui vous permet de créer un produit" +
                                            "<br>le produit créé sera enregistré" +
                                            "<br>le second sous-menu permet en premier lieu de voir tout les produits enregistrés" +
                                            "<br>vous pourrez y selectionner un ou plusieurs produit puis cliquer sur un bouton 'suprimer'" +
                                            "<br>ainsi, les produtis selectionné pourront être suprimé" +
                                            "<br>attention que supprimer un produit peut effacer également d'autres données qui y sont liées" +
                                            "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                            "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                            "<br>qu'elle n'a pas put charger" +
                                            "</p></html>");

        searchProductLabel = new JLabel("<html><p>" +
                                            "Ce sous-menu vous permet d'accéder à la même fonctionnalité" +
                                            "<br>que le sous menu 'Trouver/Modifier/supprimer'" +
                                            "ce sous-menu équivalent se trouvedans le menu 'Produit'" +
                                            "<br>ce sous-menu permet en premier lieu de voir tout les produits enregistrés" +
                                            "<br>vous pourrez y selectionner un ou plusieurs produit puis cliquer sur un bouton 'suprimer'" +
                                            "<br>ainsi, les produtis selectionné pourront être suprimé" +
                                            "<br>attention que supprimer un produit peut effacer également d'autres données qui y sont liées" +
                                            "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                            "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                            "<br>qu'elle n'a pas put charger" +
                                            "</p></html>");

        searchProductByQuantityLabel = new JLabel("<html><p>" +
                                                        "Ce sous-menu permet de Connaître la quantité des produits d’un même type " +
                                                        "<br>vendu dans un interval de temps (pour comparer les ventes de différents produits d’un même type)" +
                                                        "<br><br>Dans ce sous menu vous pourrez remplir un formulaire, il contient 3 champs :</p>" +
                                                        "<ol><li>type de produit</li>" +
                                                        "<li>date de début de la recherche</li>" +
                                                        "<li>date de fin de la recherche</li>" +
                                                        "</ol><p>le premier est une liste des types de produits existant" +
                                                        "<br>vous devez en selectionner un et la recherche se basera sur les produits de ce type" +
                                                        "<br>les deux dates déterminenet l'intervale de temps pendant lequel ont été vendus les" +
                                                        "<br>produits à chercher" +
                                                        "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                                        "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                                        "<br>qu'elle n'a pas put charger" +
                                                        "</p></html>");

        searchProductSupplementDueToEventLabel = new JLabel("<html><p>" +
                                                                "Ce sous-menu permet de Trouver tous les produits (et leur status)" +
                                                                "<br>qui ont un réassort supplémentaire lié à un évènement entre deux dates données" +
                                                                "<br>vous y trouverez un formulaire comportant deux entrées de dates" +
                                                                "<br>une pour le début de la recherche et une pour la fin, lorsque vous cliquez sur" +
                                                                "<br>le bouton de recherche, une liste des prosduits demandant un réassort s'afficheront" +
                                                                "<br>seul ceux qui sont lié à un évênement qui se déroule entre les deux dates seront affiché" +
                                                                "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                                                "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                                                "<br>qu'elle n'a pas put charger" +
                                                                "</p></html>");

        searchAdressLabel = new JLabel("<html><p>" +
                                            "Ce sous-menu permet de Trouver toutes les adresses d'une personne ou" +
                                            "<br>d'une entrerpsie" +
                                            "<br>Ce sous-menu vous donnera a ccès à une formulaire dans lequel, vous selectionnez" +
                                            "<br>une personne/entreprise et lorsque vous lancez la recherche, un tableau apparaîtra" +
                                            "<br>dans ce tableau, vous pourrez retrouver les différentes adresse de la personne/entreprise" +
                                            "<br>que vous avez selectionné" +
                                            "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                            "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                            "<br>qu'elle n'a pas put charger" +
                                            "</p></html>");

        searchProductOutOfStockLabel = new JLabel("<html><p>" +
                                                        "Ce sous-menu permet d'accéder à une tâche métier" +
                                                        "<br>Le but de cette tâche sera de vous aider à connaître les produits dont le stock est" +
                                                        "<br>insuffisant et de proposer des fournisseurs pour acheter ces produits" +
                                                        "<br>ette tâche s’effectuera en deux étapes." +
                                                        "<br>Lors de la première étape, vous aurez un formulaire dans lequel vous pourrez decider ou non" +
                                                        "<br>de rechercher uniquement les produit d'un certain type" +
                                                        "<br>Si vous sélectionnez cette option et un type de produit, la recherche ne se fera que sur celui-ci" +
                                                        "<br>Sinon, elle seffectura sur tout les produits enregistré, peu importe son type" +
                                                        "<br>lorsque vous cliquez sur le boutton de recherche, un tableau avec les produit dont le " +
                                                        "<br>stock actuel est insufisant s'affichera, vous pourrez y selectionner un produit et clicer sur" +
                                                        "<br>le bouton de recherche, celà lancera la deuxième étape, la recherche des fournisseurs pour ce produit" +
                                                        "<br>vous aurez accès à un formulaire dans le quel vous pouvez décider d'insiquer un prix ou un" +
                                                        "<br>délai de livraison maximum" +
                                                        "<br>vous pouvez faire une recherche en utilisant 1 des deux options, les deux ou aucunes" +
                                                        "<br>lorsque vous cliquez sur le bouton de recherche, un tableau avec les fournisseur s'affichera" +
                                                        "<br>vous pourrez également y retrouver le prix auquel ce fournisseur le vend et le délai de livraison" +
                                                        "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                                        "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                                        "<br>qu'elle n'a pas put charger" +
                                                        "</p></html>");

        newProductLabel = new JLabel("<html><p>" +
                                            "Ce sous-menu vous permet de créer un nouveau porduit" +
                                            "<br>vous y retrouverez un formulaire avec différents champs" +
                                            "<br>remplissez ces champ puis cliquez sur le bouton de création pour" +
                                            "<br>créer un produit avec les informations que vous avez entrées" +
                                            "<br>seul le dernier champs (description) n'est pas nécéssairement rempli pour" +
                                            "<br>pouvoir créer un produit" +
                                            "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                            "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                            "<br>qu'elle n'a pas put charger" +
                                            "</p></html>");

        findProductLabel = new JLabel("<html><p>" +
                                            "Ce sous-menu vous permet d'accéder à la même fonctionnalité" +
                                            "<br>que le sous menu 'Un produit' du menu Recherche" +
                                            "ce sous-menu équivalent se trouvedans le menu 'Produit'" +
                                            "<br>ce sous-menu permet en premier lieu de voir tout les produits enregistrés" +
                                            "<br>vous pourrez y selectionner un ou plusieurs produit puis cliquer sur un bouton 'suprimer'" +
                                            "<br>ainsi, les produtis selectionné pourront être suprimé" +
                                            "<br>attention que supprimer un produit peut effacer également d'autres données qui y sont liées" +
                                            "<br><br>si une érreur survient lorsque la page doit charger, vous en serez averti" +
                                            "<br>si c'est le cas, la page une fois chargée affichera un message qui explique " +
                                            "<br>qu'elle n'a pas put charger" +
                                            "</p></html>");


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
