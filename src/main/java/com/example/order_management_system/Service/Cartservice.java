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
}