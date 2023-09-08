package com.blinklink.servicelearning.cart;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    @PostMapping(value="/{userId}/create-cart")
    public ResponseEntity<?> createCartAndAddProduct(@PathVariable("userId") UUID userId){
        Cart cart= cartService.createCartAndAddProduct(userId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        }
        else {
            return ResponseEntity.badRequest().body("Unable to add product to cart.");
        }
    }

    @Getter
    private static class CreateResource{
        private final UUID productId;

        private final Integer quantity;

        @JsonCreator
        public CreateResource(UUID productId,Integer quantity){
            this.productId=productId;
            this.quantity=quantity;
        }
    }

    @PostMapping(value="/{cartId}/add-product")
    public ResponseEntity<?> addToCart(@PathVariable("cartId") UUID cartId,@RequestBody CreateResource createResource){
        Cart cart = cartService.addProductToCart(cartId, createResource.getProductId(),createResource.getQuantity());
        return ResponseEntity.ok(cart);
    }

    @GetMapping(value = "/cart/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable("cartId") UUID cartId){
        var cart= cartService.find(cartId);
        return ResponseEntity.ok(cart);
    }
}

