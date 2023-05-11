package userInterface;

import javax.swing.*;

public class BottlesAnimationThread extends Thread {
    private WelcomePanel panel;

    public BottlesAnimationThread(WelcomePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.changeImage();
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
