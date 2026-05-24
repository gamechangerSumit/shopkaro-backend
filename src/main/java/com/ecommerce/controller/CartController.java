package com.ecommerce.controller;

import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    // 🛒 Add to cart
    @PostMapping("/add")
    public CartItem addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String userEmail) {

        return service.addToCart(productId, quantity, userEmail);
    }

    // 📦 Get cart
    @GetMapping
    public List<CartItem> getCart(@RequestParam String userEmail) {
        return service.getUserCart(userEmail);
    }

    // ❌ Remove item
    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable Long id) {
        service.removeItem(id);
        return "Item removed";
    }

    // 🧹 Clear cart
    @DeleteMapping("/clear")
    public String clearCart(@RequestParam String userEmail) {
        service.clearCart(userEmail);
        return "Cart cleared";
    }
}