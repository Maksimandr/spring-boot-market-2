package ru.gb.springbootdemoapp.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.springbootdemoapp.dto.Cart;
import ru.gb.springbootdemoapp.service.CartService;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartRestControllerTest {

    private CartRestController cartRestController;
    private Cart cart = new Cart();

    @BeforeEach
    void setUp() {
        CartService cartService = mock(CartService.class);
        when(cartService.getCartForCurrentUser()).thenReturn(cart);
        when(cartService.addProductById(1L)).thenReturn(cart);
        when(cartService.removeProductById(1L)).thenReturn(cart);

        cartRestController = new CartRestController(cartService);
    }

    @Test
    void getCartForCurrentUser() {
        Cart currentCart = cartRestController.getCart();
        assertSame(currentCart, cart);
    }

    @Test
    void addProduct() {
        Cart currentCart = cartRestController.addProduct(1L);
        assertSame(currentCart, cart);
    }

    @Test
    void deleteProduct() {
        Cart currentCart = cartRestController.deleteProduct(1L);
        assertSame(currentCart, cart);
    }
}