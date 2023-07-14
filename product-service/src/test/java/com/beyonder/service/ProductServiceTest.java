package com.beyonder.service;

import com.beyonder.model.Product;
import com.beyonder.repository.ProductRepository;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;

import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class ProductServiceTest {

    @Inject
    ProductService productService;
    @InjectMock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAll() {
        //ASSSIGN/ARRANGE
        Product product = new Product();
        product.setName("fajri");
        product.setCode("001");
        product.setStock(10);
        product.setPriceItem(10.00);

        List<Product> products = List.of(product);

        //ACT
        when(productRepository.getAll(any(), any())).thenReturn(products);

        //ASSURE
        List<Product> actualResp = productService.getAll("fajri", "001");
        assertEquals(products.size(), actualResp.size());

    }

    @Test
    void create() {
    }


}