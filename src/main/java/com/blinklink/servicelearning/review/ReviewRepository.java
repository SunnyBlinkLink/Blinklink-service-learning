package com.blinklink.servicelearning.review;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {

    @Override
    Optional<Review> findById(UUID uuid);
}
