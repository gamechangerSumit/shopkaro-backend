package com.ecommerce.service;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    // ✅ Add to Cart
    public CartItem addToCart(Long productId, int quantity, String userEmail) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem(product, quantity, userEmail);

        return cartRepo.save(item);
    }

    // ✅ Get User Cart
    public List<CartItem> getUserCart(String userEmail) {
        return cartRepo.findByUserEmail(userEmail);
    }

    // ✅ Remove item
    public void removeItem(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }

    // ✅ Clear Cart
    public void clearCart(String userEmail) {
        List<CartItem> items = cartRepo.findByUserEmail(userEmail);
        cartRepo.deleteAll(items);
    }
}