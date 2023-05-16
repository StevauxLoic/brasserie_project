package unitTest;

import model.Exeptions.SupplierForAProductException;
import model.Product;
import model.SupplierForAProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userInterface.searchs.SuppliersForAProductSearchPanel;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuppliersForAProductSearchTest {
    private Product product;
    private SuppliersForAProductSearchPanel searchPanel;

    @BeforeEach
    public void setUp() {
        product = new Product("ref", 2,"name test",
                21, 5, true, LocalDate.of(1999, 11, 4),
                5.5, 14.7, 10, "description test");
        searchPanel = new SuppliersForAProductSearchPanel(product);
    }

    @Test
    public void getMissingProductsAmount() {
        assertEquals(0, searchPanel.getMissingProductsAmount());
    }

    @Test
    public void getAveragePriceForMissingPorducts() {
        ArrayList<SupplierForAProduct> suppliersList = new ArrayList<SupplierForAProduct>();
        try {
            suppliersList.add(
                    new SupplierForAProduct("firstSupplier", "firstRef", "fournisseur", 1.5, 4));
            suppliersList.add(
                    new SupplierForAProduct("secondSupplier", "secondRef", "fournisseur", 1.0, 2));

        } catch (SupplierForAProductException exception) {
            exception.printStackTrace();
        }
        assertEquals(1.25, searchPanel.getAveragePriceForMissingPorducts(suppliersList), 0.01);
    }
}