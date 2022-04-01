package ru.gb.springbootdemoapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.gb.springbootdemoapp.converter.ProductMapper;
import ru.gb.springbootdemoapp.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class ProductControllerTest {

    private ProductController productController;
    private Model model = mock(Model.class);

    private String getAllProducts = "product_list";
    private String getProductInfo = "product_info";

    @BeforeEach
    void setUp() {
        ProductService productService = mock(ProductService.class);
        ProductMapper productMapper = mock(ProductMapper.class);

        productController = new ProductController(productService, productMapper);
    }

    @Test
    void getAllProductsUrlTest() {
        String s = productController.getAllProducts(model);
        assertSame(s, getAllProducts);
    }

    @Test
    void getProductInfoUrlTest() {
        String s = productController.getProductInfo(1L, model);
        assertSame(s, getProductInfo);
    }
}