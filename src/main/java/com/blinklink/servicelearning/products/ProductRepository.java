package com.blinklink.servicelearning.products;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);
}

