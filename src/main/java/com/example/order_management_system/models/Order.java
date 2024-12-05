import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;
    private Date orderDate;
    private String deliveryAddress;
    private String paymentInfo;

    @Test
    void testCreateOrder() {
        User user = new User("test@example.com", "password123");
        Order order = orderService.createOrder(user, "New", "123 Address", "Credit Card");
        assertNotNull(order);
        assertEquals("New", order.getStatus());
        assertEquals("123 Address", order.getDeliveryAddress());
    }


    // Getters and Setters
}