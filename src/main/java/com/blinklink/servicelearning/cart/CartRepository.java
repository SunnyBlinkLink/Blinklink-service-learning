package com.blinklink.servicelearning.cart;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart,UUID> {
    Optional<Cart> findById(UUID Id);

}