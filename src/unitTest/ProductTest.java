package unitTest;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    public  void  setUp() {
        product = new Product("ref", 2,"name",
                21, 5, true, LocalDate.of(1999, 11, 4),
                5.5, 14.7, 10, "description test");
    }

    @Test
    public  void setDescription() {
        product.setDescription("");
        assertNull(product.getDescription());
    }

    @Test
    public  void setVat() {
        product.setVat(-25);
        assertEquals(0, product.getVat(), 0.01);
    }

    @Test
    public void setAlcoholLevel() {
        product.setAlcoholLevel(-23.48);
        assertEquals(0, product.getAlcoholLevel());
    }

    @Test
    public void setQuantityInStock() {
        product.setQuantityInStock(-15);
        assertEquals(0, product.getQuantityInStock());
    }

    @Test
    public void setMinimumQuantityInStock() {
        product.setMinimumQuantityInStock(-59);
        assertEquals(0, product.getMinimumQuantityInStock());
    }

    @Test
    public void setPrice() {
        product.setPrice(-48);
        assertEquals(0, product.getPrice());
    }

}