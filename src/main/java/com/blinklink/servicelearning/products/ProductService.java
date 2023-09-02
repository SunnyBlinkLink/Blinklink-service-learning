package com.blinklink.servicelearning.products;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product find(String name);

    List<Product> getProductsByCategory(String category);

    List<Product> getAllProducts();

}
