
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTests {

    @Test
    public void testOrderProperties() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setStatus("Pending");
        order.setOrderDate(new Date());
        order.setDeliveryAddress("123 Test Street");
        order.setPaymentInfo("Credit Card");

        assertEquals(1L, order.getId());
        assertEquals(user, order.getUser());
        assertEquals("Pending", order.getStatus());
        assertNotNull(order.getOrderDate());
        assertEquals("123 Test Street", order.getDeliveryAddress());
        assertEquals("Credit Card", order.getPaymentInfo());
    }

    @Test
    public void testOrderEqualsAndHashCode() {
        User user = new User();
        user.setId(1L);

        Order order1 = new Order();
        order1.setId(1L);
        order1.setUser(user);

        Order order2 = new Order();
        order2.setId(1L);
        order2.setUser(user);

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }
    @Test
    public void testCreateOrder() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .param("userId", "1")
                        .param("status", "pending")
                        .param("deliveryAddress", "123 Test Street")
                        .param("paymentInfo", "Credit Card"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("pending"));
    }

    @Test
    public void testCreateOrderIncomplete() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .param("userId", "1")
                        .param("status", "pending"))
                .andExpect(status().isBadRequest());
    }

}
