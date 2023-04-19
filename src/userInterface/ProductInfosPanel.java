package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductInfosPanel extends JPanel {
    private Product produit;

    JLabel nameLabel, referenceLabel, productTypeLabel, vatLabel, quantityInStockLabel, minimumQuantityInStockLabel, sparklingLabel, alcoholLevelLabel, priceLabel, launchingDateLabel;

    public ProductInfosPanel(Product produit, String buttonGoal) {
        this.setBounds(10, 80,500,150);
        this.setLayout(new GridLayout(10,2,5,5));

        nameLabel = new JLabel("nom");
        this.add(nameLabel);
        this.add(new JLabel(produit.getName()));

        referenceLabel = new JLabel("nom");
        this.add(referenceLabel);
        this.add(new JLabel(produit.getReference()));

        productTypeLabel = new JLabel("nom");
        this.add(productTypeLabel);
        this.add(new JLabel(produit.getTypeReference()));                   // ref vers un type de produit ==> trouver le nom ?

        vatLabel = new JLabel("nom");
        this.add(vatLabel);
        this.add(new JLabel("" + produit.getVat() + '%'));

        quantityInStockLabel = new JLabel("nom");
        this.add(quantityInStockLabel);
        this.add(new JLabel(produit.getQuantityInStock() + "unitée(s)"));

        minimumQuantityInStockLabel = new JLabel("nom");
        this.add(minimumQuantityInStockLabel);
        this.add(new JLabel(produit.getMinimumQuantityInStock() + "unitée(s)"));

        sparklingLabel = new JLabel("nom");
        this.add(sparklingLabel);
        this.add(new JLabel(produit.isSparkling() ? "oui": "non"));

        alcoholLevelLabel = new JLabel("nom");
        this.add(alcoholLevelLabel);
        Double alcoholLevel = produit.getAlcoholLevel();
        this.add(new JLabel(alcoholLevel != null ? alcoholLevel + " %" : "non alcoolisé"));

        priceLabel = new JLabel("nom");
        this.add(priceLabel);
        this.add(new JLabel(produit.getPrice() + "€"));

        launchingDateLabel = new JLabel("nom");
        this.add(launchingDateLabel);
        this.add(new JLabel(produit.getLaunchingDate().toString()));
    }

}