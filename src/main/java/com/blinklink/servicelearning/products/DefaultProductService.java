package com.blinklink.servicelearning.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        var existingProduct=productRepository.findByName(product.getName())
                .orElse(null);
        if(existingProduct!=null){
            return existingProduct;
        }
        return productRepository.save(product);
    }

    @Override
    public Product find(String name) {
        return productRepository.findByName(name).orElseThrow(()->new RuntimeException("no product found"));
    }


    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        Iterable<Product> productIterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        productIterable.forEach(productList::add);
        return productList;
    }
}
