package com.blinklink.servicelearning.review;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/create-review/{productId}")
    public ResponseEntity<?> createReview(@PathVariable("productId") UUID productId, @RequestBody CreateResource createResource){
        var review= new Review(createResource.getRating(),createResource.getComment());
        review=reviewService.createReview(productId,review);
        return ResponseEntity.ok(review);
    }

    @GetMapping(value = "/{productId}/getAllReviews")
    public ResponseEntity<?> getAllReviews(@PathVariable("productId") UUID productId){
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    @Getter
    private static class CreateResource{
        private final Integer Rating;
        private final String Comment;

        @JsonCreator
        public CreateResource(Integer Rating, String Comment){
            this.Rating=Rating;
            this.Comment=Comment;
        }
    }
}
