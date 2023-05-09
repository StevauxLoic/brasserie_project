package userInterface;

import model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductInfosPanel extends JPanel {
    private Product produit;

    private JLabel nameLabel,
            referenceLabel,
            productTypeLabel,
            vatLabel,
            quantityInStockLabel,
            minimumQuantityInStockLabel,
            sparklingLabel,
            alcoholLevelLabel,
            priceLabel,
            launchingDateLabel,
            descriptionLabel,
            erroMessageLabel;

    // show the product infos or only a message 'no product found' if the given product is null
    public ProductInfosPanel(Product product) {
        this.setLayout(new GridLayout(11,2,5,5));

        // fill the form with the information about the product to modify
        if (product != null) {
            nameLabel = new JLabel("nom");
            this.add(nameLabel);
            this.add(new JLabel(product.getName()));

            referenceLabel = new JLabel("référence");
            this.add(referenceLabel);
            this.add(new JLabel(product.getReference()));

            productTypeLabel = new JLabel("référeance du type de product");
            this.add(productTypeLabel);
            this.add(new JLabel(String.valueOf(product.getTypeReference())));            // ref en int

            vatLabel = new JLabel("TVA");
            this.add(vatLabel);
            this.add(new JLabel("" + product.getVat() + '%'));

            quantityInStockLabel = new JLabel("quantité en stock");
            this.add(quantityInStockLabel);
            this.add(new JLabel(product.getQuantityInStock() + "unitée(s)"));

            minimumQuantityInStockLabel = new JLabel("quantité minimum en stock");
            this.add(minimumQuantityInStockLabel);
            this.add(new JLabel(product.getMinimumQuantityInStock() + "unitée(s)"));

            sparklingLabel = new JLabel("est pétillant");
            this.add(sparklingLabel);
            this.add(new JLabel(product.isSparkling() ? "oui" : "non"));

            alcoholLevelLabel = new JLabel("niveau d'alcool");
            this.add(alcoholLevelLabel);
            Double alcoholLevel = product.getAlcoholLevel();
            this.add(new JLabel(alcoholLevel != null ? alcoholLevel + "%" : "non alcoolisé"));

            priceLabel = new JLabel("prix HTVA (en magasin)");
            this.add(priceLabel);
            this.add(new JLabel(product.getPrice() + "€"));

            launchingDateLabel = new JLabel("date de lancement");
            this.add(launchingDateLabel);
            this.add(new JLabel(product.getLaunchingDate().toString()));

            descriptionLabel = new JLabel("description");
            this.add(descriptionLabel);
            String productDescrpition = product.getDescription();
            this.add(new JLabel(productDescrpition != null ? productDescrpition : "pas de description"));

        } else {
            erroMessageLabel = new JLabel("Pas de produit trouvé (erreur)");
            this.add(erroMessageLabel);
        }
    }
}