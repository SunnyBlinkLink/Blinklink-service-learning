package com.blinklink.servicelearning.products;

import com.blinklink.servicelearning.MonetaryAmountConverter;
import com.blinklink.servicelearning.MonetaryAmountSerializer;
import com.blinklink.servicelearning.review.Review;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;
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

    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Review> reviews=new ArrayList<>();

    public Product(String name, String category, MonetaryAmount price,Integer quantity){
        this.id=UUID.randomUUID();
        this.name=name;
        this.category=category;
        this.price=price;
        this.quantity=quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public List<Review> getReviews(){
        return reviews;
    }

}



