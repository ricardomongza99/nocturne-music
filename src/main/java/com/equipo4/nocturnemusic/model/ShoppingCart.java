package com.equipo4.nocturnemusic.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashMap;
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
    
    public ShoppingCart() {
    	this.items = new HashMap<>();
    }

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
    	for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                int quantity = entry.getValue();
                items.put(entry.getKey(), quantity + 1);
                return;
            }
        }
        items.put(product, 1);
    }

    public int findItem(Product product) {
    	for (Map.Entry<Product, Integer> item : items.entrySet()) {
            Product i = item.getKey();
            if (i.getId().equals(product.getId())) {
                return item.getValue();
            }
        }
        return 0;
    }
    
    public void updateItem(Product product, int quantity) {
    	for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
            	if (quantity > 0) {
                    items.put(entry.getKey(), quantity);
                } else {
                    items.remove(entry.getKey());
                }
            	return;
            }
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
