package ru.gb.springbootdemoapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("1");
        product1.setPrice(100.f);
        productList.add(product1);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("2");
        product2.setPrice(200.f);
        productList.add(product2);
        Product product3 = new Product();
        product3.setId(3L);
        product3.setTitle("3");
        product3.setPrice(300.f);
        productList.add(product3);
        given(productRepository.findAll()).willReturn(productList);
        given(productRepository.findById(1L)).willReturn(Optional.of(product1));
    }

    @Test
    void getAllListProductsTest() {
        List<Product> productList = productRepository.findAll();
        assertSame(productList.size(), 3);
        assertEquals(productList.get(0).getId(), 1);
        assertEquals(productList.get(1).getTitle(), "2");
        assertEquals(productList.get(2).getPrice(), 300.f);
    }

    @Test
    void findByIdExistProductTest() {
        Product product = productRepository.findById(1L).orElse(null);
        assertNotNull(product);
        assertEquals(product.getId(), 1);
        assertEquals(product.getTitle(), "1");
        assertEquals(product.getPrice(), 100.f);
    }

    @Test
    void findByIdNotExistProductTest() {
        Product product = productRepository.findById(10L).orElse(null);
        assertNull(product);
    }

    @Test
    void saveProductCallTest() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("1");
        product.setPrice(100.f);
        productService.save(product);
        verify(productRepository).save(product);
    }

    @Test
    void deleteProductCallTest() {
        productService.deleteById(1L);
        verify(productRepository).deleteById(1L);
    }
}