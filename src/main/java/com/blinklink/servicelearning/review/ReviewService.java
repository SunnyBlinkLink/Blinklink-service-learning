package com.blinklink.servicelearning.review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    Review createReview(UUID productId, Review review);

    List<Review> getReviewsByProduct(UUID productId);

}
