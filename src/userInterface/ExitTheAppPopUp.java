package userInterface;

import Controller.ShopController;
import model.Exeptions.CloseConnectionException;
import model.Exeptions.CreateConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitTheAppPopUp extends PopUp {

    JLabel exitQuestion;
    JPanel textPanel, buttonsPanel;
    JButton cancelButton, exitButton;

    public ExitTheAppPopUp() {
        super("Quiter l'application ?", 360, 100);
    }

    @Override
    public void fillThePopUpFrame() {
        this.setLayout(new BorderLayout());

        // listener
        ButtonListener buttonListener = new ButtonListener();

        // panels
        textPanel = new JPanel();
        buttonsPanel = new JPanel();

        // label
        exitQuestion = new JLabel("Voulez vous quitter l'application ?");
        textPanel.add(exitQuestion);

        cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(buttonListener);
        buttonsPanel.add(cancelButton);

        exitButton = new JButton("Quitter");
        exitButton.addActionListener(buttonListener);
        buttonsPanel.add(exitButton);

        // adding the panels
        this.add(textPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == cancelButton) {
                ExitTheAppPopUp.this.dispose();
            } else if(source == exitButton) {   // could be just a 'if' instead of an 'if else'
                try {
                    ShopController shopController = new ShopController();
                    shopController.closeConnection();
                } catch (CreateConnectionException  exceptionEvent) {
                    // TODO on écrit qq chose ici ?
                } catch (CloseConnectionException exceptionEvent) {
                    JOptionPane.showMessageDialog(null, "la fermeture de la connection aux données a échoué", "fermeture de la connection", JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
            }
        }
    }
}
