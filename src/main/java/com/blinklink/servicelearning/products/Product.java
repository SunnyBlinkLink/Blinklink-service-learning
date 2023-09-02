package com.blinklink.servicelearning.products;

import com.blinklink.servicelearning.MonetaryAmountConverter;
import com.blinklink.servicelearning.MonetaryAmountSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import java.util.UUID;

@Getter
@Entity
@Table(name="bl_product")
@NoArgsConstructor(force = true)
public class Product {

    @Id
    private UUID id;

    private String name;

    private String category;

    @JsonSerialize(using = MonetaryAmountSerializer.class)
    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "price", nullable = false)
    private MonetaryAmount price;

    public Product(String name, String category, MonetaryAmount price){
        this.id=UUID.randomUUID();
        this.name=name;
        this.category=category;
        this.price=price;
    }

}

