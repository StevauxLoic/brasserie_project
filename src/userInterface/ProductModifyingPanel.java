package userInterface;

import javax.swing.*;

public class ProductModifyingPanel extends JPanel {
    public ProductModifyingPanel() {
        this.add(new ProductSearchForm());

        this.setVisible(true);
    }
}
