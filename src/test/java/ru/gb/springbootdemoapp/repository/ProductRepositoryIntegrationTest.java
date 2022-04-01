package ru.gb.springbootdemoapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.gb.springbootdemoapp.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProductRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void getAllProductsWithCategoryTest() {
        Product product1 = new Product();
        product1.setTitle("1");
        product1.setPrice(100.f);
        Product product2 = new Product();
        product2.setTitle("2");
        product2.setPrice(200.f);
        Product product3 = new Product();
        product3.setTitle("3");
        product3.setPrice(300.f);

        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);

        List<Product> productList = productRepository.findProductsByTitle("2");
        System.out.println(productList);

        assertEquals(1, productList.size());
        assertTrue(productList.stream().allMatch(p -> p.getTitle().equals("2")));
    }

}