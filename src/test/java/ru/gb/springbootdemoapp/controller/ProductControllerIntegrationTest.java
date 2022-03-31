package ru.gb.springbootdemoapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllProductsHttpStatusTest() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/", String.class);
        assertSame(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getProductInfoHttpStatusTest() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/info/1", String.class);
        assertSame(entity.getStatusCode(), HttpStatus.OK);
    }
}