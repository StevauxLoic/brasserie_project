package userInterface;

import javax.swing.*;
import java.awt.*;

public class ProductSearchPanel extends JPanel {
    public ProductSearchPanel() {
        this.add(new ProductSearchForm());

        this.setVisible(true);
    }
}
