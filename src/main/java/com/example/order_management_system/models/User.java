import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @Test
    void testRegisterUser() {
        User user = new User("test@example.com", "password123");
        User registeredUser = userService.registerUser(user.getEmail(), user.getPassword());
        assertNotNull(registeredUser);
        assertEquals("test@example.com", registeredUser.getEmail());
    }
    @Test
    void testLogin() {
        Optional<User> user = userService.findByEmail("test@example.com");
        assertTrue(user.isPresent());
        assertEquals("test@example.com", user.get().getEmail());
    }


    // Getters and Setters
}