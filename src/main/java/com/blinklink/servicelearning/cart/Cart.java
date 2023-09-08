package com.blinklink.servicelearning.cart;

import com.blinklink.servicelearning.MonetaryAmountConverter;
import com.blinklink.servicelearning.MonetaryAmountSerializer;
import com.blinklink.servicelearning.products.Product;
import com.blinklink.servicelearning.products.ProductRepository;
import com.blinklink.servicelearning.user.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class Cart extends AbstractAggregateRoot<Cart> {

    @Id
    private final UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_products", indexes = {@Index(columnList = "products")})
    private Map<UUID,Integer> products = new HashMap<>();
    private UUID userId;

    @Convert(converter= MonetaryAmountConverter.class)
    @Column(name = "total_amount")
    @JsonSerialize(using = MonetaryAmountSerializer.class)
    private MonetaryAmount totalAmount=Money.zero(Monetary.getCurrency("USD"));

    public Cart(User user) {
        this.id = UUID.randomUUID();
        this.userId = user.getId();
    }

    Cart linkProduct(UUID productId,Integer quantity,ProductRepository productRepository) {
        products.put(productId,quantity);
        calculateTotal(productRepository);
        return this;
    }

    private void calculateTotal(ProductRepository productRepository){
        MonetaryAmount Total = Money.of(BigDecimal.ZERO, "USD");
        for(Map.Entry<UUID,Integer> entry:products.entrySet()) {
            UUID productId=entry.getKey();
            Integer quantity=entry.getValue();
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            Total = Total.add(product.getPrice().multiply(quantity));
            product.setQuantity(product.getQuantity()-quantity);
        }
        this.totalAmount=Total;
        System.out.println(totalAmount);
    }
}

