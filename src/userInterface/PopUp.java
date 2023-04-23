package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class PopUp extends JFrame {

    Container mainContainer;
    int windowHeigt, windowwidht;

    public PopUp(String windowName, int windowwidht, int windowHeigt) {

        // constructor and variables
        super(windowName);
        this.windowHeigt = windowHeigt;
        this.windowwidht = windowwidht;

        // pop-up window
        setSize(windowwidht, windowHeigt);
        setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PopUp.this.dispose();
            }
        });


        // pop-up filling
        mainContainer = new Container();
        fillTheContainer();

        this.add(mainContainer);

        // display the pop-up
        setVisible(true);
    }

    public PopUp(String windowName) throws HeadlessException {
        this(windowName, 500, 500);
    }

    public abstract void fillTheContainer();
}
