package userInterface;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private JLabel welcomeMessage;
    private int iImage;
    private BottlesAnimationThread bottlesAnimationThread;


    public WelcomePanel() {
        welcomeMessage = new JLabel("<html><p style=\"text-align: center;\"><image src=\"https://pngimg.com/d/welcome_PNG81.png\" alt=\"l'image n'a put charger\" width=\"220\" height=\"100\"><br>Bienvenue sur le gestionnaire" +
                "<br>Le manuel d'utilisation se trouve dans le menu aide/Manuel d'utilisation</p></html>");
        add(welcomeMessage);

        iImage = 0;

        bottlesAnimationThread = new BottlesAnimationThread(this);
        bottlesAnimationThread.start();
    }

    public void stopBottlesAnimation() {
        bottlesAnimationThread.stop();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image currentImage = toolkit.getImage(System.getProperty("user.dir") + "\\src\\userInterface\\images\\bottles-" + iImage + ".png");
        g.drawImage(currentImage, 375, 200, 250, 200, this);
    }

    public void changeImage() {
        iImage++;
        if (iImage > 7) {
            iImage = 0;
        }
        repaint();
    }
}
