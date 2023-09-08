package com.blinklink.servicelearning.cart;

import java.util.UUID;

public interface CartService {

    Cart createCartAndAddProduct(UUID userId);

    Cart addProductToCart(UUID cartId, UUID productId, Integer quantity);

    Cart find(UUID Id);
}
