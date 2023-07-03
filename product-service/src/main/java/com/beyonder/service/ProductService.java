package com.beyonder.service;

import com.beyonder.dto.request.CreateProductReqDTO;
import com.beyonder.model.Product;
import com.beyonder.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;
    public void create(CreateProductReqDTO createProductReqDTO) {
        Product product = new Product();
        product.setName(createProductReqDTO.getName());
        product.setCode("001");
        product.setStock(createProductReqDTO.getQuantity());
        product.setPrice(createProductReqDTO.getPrice());

        productRepository.persist(product);
    }
}
