package com.beyonder.service;

import com.beyonder.dto.request.CreateProductReqDTO;
import com.beyonder.model.Product;
import com.beyonder.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;

    @Transactional
    public void create(CreateProductReqDTO createProductReqDTO) {
        Product product = new Product();
        product.setName(createProductReqDTO.getName());
        product.setCode("001");
        product.setStock(createProductReqDTO.getQuantity());
        product.setPriceItem(createProductReqDTO.getPrice());

        productRepository.persist(product);
    }
}
