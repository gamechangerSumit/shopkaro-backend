package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    // Constructor Injection (Best Practice 🔥)
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // ✅ Get All Products
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // ✅ Add Product
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    // ✅ Get Product By ID
    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // ✅ Update Product
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStock(updatedProduct.getStock());

        return repo.save(existing);
    }

    // ✅ Delete Product
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        repo.deleteById(id);
    }
}