package com.blinklink.servicelearning.cart;

import com.blinklink.servicelearning.products.ProductRepository;
import com.blinklink.servicelearning.user.User;
import com.blinklink.servicelearning.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultCartService implements CartService{

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart createCartAndAddProduct(UUID userId) {
        User user= userRepository.findById(userId)
                .orElse(null);
        assert user != null;
        var cart = new Cart(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(UUID cartId, UUID productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElse(null);
        assert cart != null;
        cart = cart.linkProduct(productId,productRepository);
        return cartRepository.save(cart);
    }

    @Override
    public Cart find(UUID Id) {
        return cartRepository.findById(Id).orElseThrow(()-> new RuntimeException("cart not found"));
    }
}

