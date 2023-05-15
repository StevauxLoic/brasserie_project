package unitTest;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userInterface.SuppliersForAProductSearchPanel;

import java.time.LocalDate;

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
}