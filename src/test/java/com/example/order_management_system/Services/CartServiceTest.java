import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CartServiceTests {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart();
    }

    @Test
    public void testAddItemToCart() {
        CartItem item = new CartItem();
        item.setProductName("Test Product");
        item.setPrice(10.0);

        when(cartRepository.save(cart)).thenReturn(cart);

        cartService.addItemToCart(cart, item);

        assertTrue(cart.getItems().contains(item), "Cart should contain the added item.");
        verify(cartRepository, times(1)).save(cart);
    }
}
