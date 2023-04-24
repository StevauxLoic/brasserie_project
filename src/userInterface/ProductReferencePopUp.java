package userInterface;

import javax.swing.*;
import java.awt.*;

public class ProductReferencePopUp extends PopUp {
    JLabel referenceLabel;

    public ProductReferencePopUp(String referenceToDisplay) {
        super("référence du produit", 250, 100);
        referenceLabel.setText(referenceToDisplay);
    }

    @Override
    public void fillThePopUpFrame() {
        this.setLayout(new FlowLayout());
        referenceLabel = new JLabel();
        this.add(referenceLabel);
    }
}
