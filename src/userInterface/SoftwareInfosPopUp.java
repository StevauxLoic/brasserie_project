package userInterface;

import javax.swing.*;

public class SoftwareInfosPopUp extends PopUp {

    private JLabel infosLabel;

    public SoftwareInfosPopUp(){
        super("information de l'application", 450, 125);
    }

    @Override
    public void fillTheContainer() {
        infosLabel = new JLabel(
                "<html><p style=\"text-align: center;\">" +
                        "logiciel de managment pour un magasin qui vend des boissons réalisé" +
                        "<br>pour le cours de programation orientée objet" +
                        "<br><br>développeurs : Castado C. & Stevaux L." +
                        "<p></html>");
        this.add(infosLabel);
    }
}
