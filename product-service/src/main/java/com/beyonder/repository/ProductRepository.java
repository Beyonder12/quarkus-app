package com.beyonder.repository;

import com.beyonder.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
    public List<Product> getAll(String name, String code) {
        return find("stock = ?1 and code = ?2", Sort.by("name").descending(), 1, code)
                .page(Page.of(1, 5))
                .list();
    }
}
