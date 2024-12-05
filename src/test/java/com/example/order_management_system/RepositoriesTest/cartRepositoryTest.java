
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartRepositoryTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindByUserId() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password123");
        entityManager.persist(user);

        Cart cart = new Cart();
        cart.setUser(user);
        entityManager.persist(cart);

        Cart foundCart = cartRepository.findByUserId(user.getId());
        assertNotNull(foundCart);
        assertEquals(user, foundCart.getUser());
    }
}
