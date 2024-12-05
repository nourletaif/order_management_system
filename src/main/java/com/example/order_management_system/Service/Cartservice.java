import com.example.onlineorders.models.Cart;
import com.example.onlineorders.models.CartItem;
import com.example.onlineorders.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addItemToCart(Cart cart, CartItem item) {
        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Cart cart, CartItem item) {
        cart.getItems().remove(item);
        cartRepository.save(cart);
    }

    public void updateItemInCart(Cart cart, CartItem item) {
        cart.getItems().removeIf(existingItem -> existingItem.getId().equals(item.getId()));
        cart.getItems().add(item);
        cartRepository.save(cart);
    }
    @Test
    void testAddItemToCart() {
        Cart cart = cartService.getCartByUserId(1L);
        CartItem item = new CartItem("Product1", 1, 19.99);
        cartService.addItemToCart(cart, item);
        assertTrue(cart.getItems().contains(item));
    }
    @Test
    void testRemoveItemFromCart() {
        Cart cart = cartService.getCartByUserId(1L);
        CartItem item = new CartItem("Product1", 1, 19.99);
        cartService.addItemToCart(cart, item);
        cartService.removeItemFromCart(cart, item);
        assertFalse(cart.getItems().contains(item));
    }

}