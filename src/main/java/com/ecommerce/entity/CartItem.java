package com.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    // 🛒 किस product का item है
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // 👤 किस user का cart
    private String userEmail;

    public CartItem() {}

    public CartItem(Product product, int quantity, String userEmail) {
        this.product = product;
        this.quantity = quantity;
        this.userEmail = userEmail;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}