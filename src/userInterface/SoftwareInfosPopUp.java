package userInterface;

import javax.swing.*;

public class SoftwareInfosPopUp extends PopUp {

    private JLabel infosLabel;

    public SoftwareInfosPopUp(){
        super("information de l'application");
    }

    @Override
    public void fillTheContainer() {
        infosLabel = new JLabel(
                "<html><p>" +
                        "logiciel de managment pour un magasin qui vend des boissons réalisé" +
                        "</br>pour le cours de programation orientée objet" +
                        "</br> développeurs : Castado C. & Stevaux L." +
                        "</p></html>");
        this.add(infosLabel);
    }
}
