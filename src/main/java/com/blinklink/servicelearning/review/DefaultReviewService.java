package com.blinklink.servicelearning.review;

import com.blinklink.servicelearning.products.Product;
import com.blinklink.servicelearning.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Override
    public Review createReview(UUID productId, Review review) {
        Product product=productRepository.findById(productId).orElse(null);
        product.addReview(review);
        productRepository.save(product);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(UUID productId) {
        Product product=productRepository.findById(productId).orElse(null);
        return product.getReviews();
    }
}
