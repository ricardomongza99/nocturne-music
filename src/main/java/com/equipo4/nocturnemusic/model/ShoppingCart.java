package com.equipo4.nocturnemusic.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Map;

public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ElementCollection
    @CollectionTable(name = "shopping_cart_items", joinColumns = @JoinColumn(name = "shopping_cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> items;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    public void addItem(Product product) {
        int quantity = items.getOrDefault(product, 0);
        items.put(product, quantity + 1);
    }

    public void updateItem(Product product, int quantity) {
        if (quantity > 0) {
            items.put(product, quantity);
        } else {
            items.remove(product);
        }
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public BigDecimal getTotalCost() {
        return items.entrySet().stream()
                .map(entry ->entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalItems() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
}
