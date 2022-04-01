package ru.gb.springbootdemoapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.springbootdemoapp.dto.Cart;
import ru.gb.springbootdemoapp.dto.CartItem;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.repository.OrderRepository;
import ru.gb.springbootdemoapp.repository.ProductRepository;
import ru.gb.springbootdemoapp.repository.UserRepository;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OrderServiceIntegrationTest {

    private OrderService orderService;
    private String address = "address";
    private String email = "abc@de.fg";

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private CartService cartService;
    @MockBean
    private Principal principal;

    @BeforeEach
    void setUp() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setProductId(1L);
        cartItem.setTitle("1");
        cartItem.setPricePerOne(10.f);
        cartItem.setPrice(10.f);
        cartItem.setCount(1);
        cart.addItem(cartItem);

        given(cartService.getCartForCurrentUser()).willReturn(cart);

        orderService = new OrderService(orderRepository, cartService, userRepository, productRepository);
    }

    @Test
    void placeOrderTest() {
        Order order = orderService.placeOrder(address, email, principal);
        assertEquals(order.getPrice(), 10.0);
        assertEquals(order.getOrderItems().size(), 1);
    }

}