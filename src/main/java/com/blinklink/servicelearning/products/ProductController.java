package com.blinklink.servicelearning.products;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController{

    private final ProductService productService;

    @PostMapping(value="/products")
    public ResponseEntity<?> create(@RequestBody CreateResource createResource){
        var product=new Product(createResource.getName(),createResource.getCategory(),createResource.getPrice());
        product=productService.create(product);
        return ResponseEntity.ok(product);
    }

    @Getter
    private static class CreateResource{
        private final String name;
        private final String category;
        private final MonetaryAmount price;

        @JsonCreator
        public CreateResource(String name,String category, BigDecimal price){
            this.name=name;
            this.category=category;
            this.price= Money.of(price, "USD");
        }
    }

    @GetMapping(value = "/products/{name}")
    public ResponseEntity<?> getProduct(@PathVariable("name") String name){
        var product=productService.find(name);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/products-by-category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable("category") String category){
        List<Product> products=productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/get-products")
    public ResponseEntity<?> getProducts(){
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

}

