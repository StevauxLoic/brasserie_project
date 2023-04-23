package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitTheAppPopUp extends PopUp {

    JLabel exitQuestion;
    JButton cancelButton, exitButton;

    public ExitTheAppPopUp() {
        super("Quiter l'application ?", 400, 100);
    }

    @Override
    public void fillTheContainer() {
        exitQuestion = new JLabel("Voulez vous quitter l'application ?");

        cancelButton = new JButton("Annuler");
        exitButton = new JButton("Quitter");
        ButtonListener buttonListener = new ButtonListener();
        cancelButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        mainContainer.add(exitQuestion, BorderLayout.CENTER);
        mainContainer.add(cancelButton, BorderLayout.SOUTH);
        mainContainer.add(exitButton, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == cancelButton) {
                ExitTheAppPopUp.this.dispose();
            } else if(source == exitButton) {   // could be just a 'if' instead of an 'if else'
                System.exit(0);
            }
        }
    }
}
