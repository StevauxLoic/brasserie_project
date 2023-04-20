package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SoftwareInfosWindow extends JFrame {

    private JLabel infosLabel;

    public SoftwareInfosWindow(){
        super("information de l'application");
        setBounds(100, 100, 500, 500);

        this.setLayout(new FlowLayout());

        infosLabel = new JLabel(
                "<html><p>" +
                    "logiciel de managment pour un magasin qui vend des boissons réalisé" +
                    "</br>pour le cours de programation orientée objet" +
                    "</br> développeurs : Castado C. & Stevaux L." +
                    "</p></html>");
        this.add(infosLabel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SoftwareInfosWindow.this.dispose();
            }
        });

        setVisible(true);
    }
}
