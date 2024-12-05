@SpringBootTest
@AutoConfigureMockMvc
public class OrderIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testOrderPlacement() throws Exception {
        // Create a test user
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Place an order
        mockMvc.perform(post("/api/orders")
                        .param("userId", user.getId().toString())
                        .param("status", "pending")
                        .param("deliveryAddress", "123 Main St")
                        .param("paymentInfo", "Credit Card"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("pending"));

        // Validate the order in the database
        List<Order> orders = orderRepository.findByUser(user);
        assertFalse(orders.isEmpty());
        assertEquals("pending", orders.get(0).getStatus());
    }
}
