package unitTest;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FirstTestTest {

    // voilà deux exemples de tests, simples et rapide
    // execute cette classe (clic droit, 'run ...' dans la fenêtre à gauche)

    private Product product;

    @BeforeEach
    public void  setUp() {
        product = new Product("ref", 2,"name",
                21.0, 5, true, LocalDate.now(),
                5.5, 14.7, 10, "description test");
    }

    @Test
    public void getReference() {
        assertEquals("ref", product.getReference());
    }

    @Test
    public void getDescription() {
        assertEquals("description test", product.getDescription());
    }

    // mal fait exprès pour te montrer, met le 6 à 21 si tu veux qu'il soit bon, car j'ai juste mis une valeur random pour montrer une erreur
    @Test
    public void getVat() {
        assertEquals(6, product.getVat());
    }

}