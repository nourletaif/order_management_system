
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTests {

    @Test
    public void testCartItemProperties() {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductName("Test Product");
        cartItem.setQuantity(2);
        cartItem.setPrice(50.0);

        assertEquals(1L, cartItem.getId());
        assertEquals("Test Product", cartItem.getProductName());
        assertEquals(2, cartItem.getQuantity());
        assertEquals(50.0, cartItem.getPrice());
    }

    @Test
    public void testEqualsAndHashCode() {
        CartItem item1 = new CartItem();
        item1.setId(1L);
        item1.setProductName("Test Product");

        CartItem item2 = new CartItem();
        item2.setId(1L);
        item2.setProductName("Test Product");

        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
}
