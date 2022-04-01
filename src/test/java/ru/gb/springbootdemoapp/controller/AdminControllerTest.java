package ru.gb.springbootdemoapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.gb.springbootdemoapp.converter.ProductMapper;
import ru.gb.springbootdemoapp.dto.ProductShortDto;
import ru.gb.springbootdemoapp.service.CategoryService;
import ru.gb.springbootdemoapp.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class AdminControllerTest {

    private AdminController adminController;
    private Model model = mock(Model.class);
    private ProductService productService = mock(ProductService.class);
    private CategoryService categoryService = mock(CategoryService.class);
    private ProductMapper productMapper = mock(ProductMapper.class);
    private ProductShortDto productShortDto = mock(ProductShortDto.class);
    private BindingResult bindingResult = mock(BindingResult.class);

    private String getAllProducts = "admin/index";
    private String getProductAddFrom = "admin/add_product_form";
    private String saveProduct = "redirect:/admin";
    private String deleteProduct = "redirect:/admin";

    @BeforeEach
    void setUp() {
        adminController = new AdminController(productService, categoryService, productMapper);
    }

    @Test
    void getAllProductsTest() {
        String s = adminController.getAllProducts(model);
        assertSame(s, getAllProducts);
    }

    @Test
    void getProductAddFromTest() {
        String s = adminController.getProductAddFrom(model);
        assertSame(s, getProductAddFrom);
    }

    @Test
    void saveProductTest() {
        String s = adminController.saveProduct(productShortDto, bindingResult, model);
        assertSame(s, saveProduct);
    }

    @Test
    void deleteProductTest() {
        String s = adminController.deleteProduct(1L);
        assertSame(s, deleteProduct);
    }
}