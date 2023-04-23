package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitTheAppPopUp extends PopUp {

    JLabel exitQuestion;
    JButton cancelButton, exitButton;

    public ExitTheAppPopUp() {
        super("Quiter l'application ?", 360, 100);
    }

    @Override
    public void fillThePopUpFrame() {
        this.setLayout(new BorderLayout());
        exitQuestion = new JLabel("Voulez vous quitter l'application ?");

        cancelButton = new JButton("Annuler");
        exitButton = new JButton("Quitter");
        ButtonListener buttonListener = new ButtonListener();
        cancelButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);

        this.add(exitQuestion, BorderLayout.CENTER);
        this.add(cancelButton, BorderLayout.WEST);
        this.add(exitButton, BorderLayout.EAST);
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
