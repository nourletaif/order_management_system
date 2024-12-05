
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CartTests {

    @Test
    public void testCartItemsRelationship() {
        Cart cart = new Cart();
        CartItem item1 = new CartItem();
        item1.setProductName("Product 1");
        CartItem item2 = new CartItem();
        item2.setProductName("Product 2");

        cart.setItems(new ArrayList<>());
        cart.getItems().add(item1);
        cart.getItems().add(item2);

        assertEquals(2, cart.getItems().size());
        assertTrue(cart.getItems().contains(item1));
        assertTrue(cart.getItems().contains(item2));
    }
}
