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
    public  void getReference() {
        assertEquals("ref", product.getReference());
    }

    @Test
    public  void getDescription() {
        assertEquals("description test", product.getDescription());
    }

    @Test
    public  void setDescription() {
        product.setDescription("");
        assertNull(product.getDescription());
    }

    @Test
    public  void getVat() {
        assertEquals(21, product.getVat(), 0.01);
    }

    @Test
    public  void setVat() {
        product.setVat(-25);
        assertEquals(0, product.getVat(), 0.01);
    }

    @Test
    public void isSparkling() {
        assertTrue(product.isSparkling());
    }

    @Test
    public void setTypeReference() {
        product.setTypeReference(12);
        assertEquals(12, product.getTypeReference());
    }

    @Test
    public void setLaunchingDate() {
        product.setLaunchingDate(LocalDate.of(2003, 9, 12));
        assertEquals(LocalDate.of(2003, 9, 12), product.getLaunchingDate());
    }

    @Test
    public void setReference() {
        product.setReference("reference test");
        assertEquals("reference test", product.getReference());
    }

    @Test
    public void setName() {
        product.setName("test name");
        assertEquals("test name", product.getName());
    }

    @Test
    public void setAlcoholLevel() {
        product.setAlcoholLevel(-23.48);
        assertEquals(0, product.getAlcoholLevel());
    }

    @Test
    public void setSparkling() {
        product.setSparkling(true);
        assertEquals(true, product.isSparkling());
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

    @Test
    public void getTypeReference() {
        assertEquals("ref", product.getReference());
    }

    @Test
    public void getName() {
        assertEquals("name", product.getName());
    }

    @Test
    public void getQuantityInStock() {
        assertEquals(10, product.getQuantityInStock());
    }

    @Test
    public void getMinimumQuantityInStock() {
        assertEquals(5, product.getMinimumQuantityInStock());
    }

    @Test
    public void getAlcoholLevel() {
        assertEquals(14.7, product.getAlcoholLevel(), 0.01);
    }

    @Test
    public void getLaunchingDate() {
        assertEquals(LocalDate.of(1999, 11, 4), product.getLaunchingDate());
    }

    @Test
    public void getPrice() {
        assertEquals(5.5, product.getPrice(), 0.01);
    }

}