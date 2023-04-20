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

        referenceLabel = new JLabel("référence");
        this.add(referenceLabel);
        this.add(new JLabel(produit.getReference()));

        productTypeLabel = new JLabel("référeance du type de produit");
        this.add(productTypeLabel);
        this.add(new JLabel(String.valueOf(produit.getTypeReference())));            // ref en int

        vatLabel = new JLabel("TVA");
        this.add(vatLabel);
        this.add(new JLabel("" + produit.getVat() + '%'));

        quantityInStockLabel = new JLabel("quantité en stock");
        this.add(quantityInStockLabel);
        this.add(new JLabel(produit.getQuantityInStock() + "unitée(s)"));

        minimumQuantityInStockLabel = new JLabel("quantité minimum en stock");
        this.add(minimumQuantityInStockLabel);
        this.add(new JLabel(produit.getMinimumQuantityInStock() + "unitée(s)"));

        sparklingLabel = new JLabel("est pétillant");
        this.add(sparklingLabel);
        this.add(new JLabel(produit.isSparkling() ? "oui": "non"));

        alcoholLevelLabel = new JLabel("niveau d'alcool");
        this.add(alcoholLevelLabel);
        Double alcoholLevel = produit.getAlcoholLevel();
        this.add(new JLabel(alcoholLevel != null ? alcoholLevel + "%" : "non alcoolisé"));

        priceLabel = new JLabel("prix HTVA (en magasin)");
        this.add(priceLabel);
        this.add(new JLabel(produit.getPrice() + "€"));

        launchingDateLabel = new JLabel("date de lancement");
        this.add(launchingDateLabel);
        this.add(new JLabel(produit.getLaunchingDate().toString()));
    }

}