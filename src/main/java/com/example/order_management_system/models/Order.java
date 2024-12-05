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

    // Getters and Setters
}