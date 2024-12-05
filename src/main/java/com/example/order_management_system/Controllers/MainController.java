import com.example.onlineorders.models.Cart;
import com.example.onlineorders.models.CartItem;
import com.example.onlineorders.models.Order;
import com.example.onlineorders.models.User;
import com.example.onlineorders.services.CartService;
import com.example.onlineorders.services.EmailService;
import com.example.onlineorders.services.OrderService;
import com.example.onlineorders.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user.getEmail(), user.getPassword());
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestBody User user) {
        return userService.findByEmail(user.getEmail());
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestParam Long userId,
                             @RequestParam String status,
                             @RequestParam String deliveryAddress,
                             @RequestParam String paymentInfo) {
        Optional<User> user = userService.findByEmail(userId.toString());
        if (user.isPresent()) {
            Order order = orderService.createOrder(user.get(), status, deliveryAddress, paymentInfo);
            emailService.sendEmail(user.get().getEmail(), "Order Confirmation", "Your order has been placed!");
            return order;
        }
        throw new RuntimeException("User not found");
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/cart/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/cart/{userId}/add")
    public Cart addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        Cart cart = cartService.getCartByUserId(userId);
        cartService.addItemToCart(cart, item);
        return cart;
    }

    @PostMapping("/cart/{userId}/remove")
    public Cart removeItemFromCart(@PathVariable Long userId, @RequestBody CartItem item) {
        Cart cart = cartService.getCartByUserId(userId);
        cartService.removeItemFromCart(cart, item);
        return cart;
    }
}