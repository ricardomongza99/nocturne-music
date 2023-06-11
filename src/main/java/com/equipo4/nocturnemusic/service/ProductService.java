package com.equipo4.nocturnemusic.service;

import com.equipo4.nocturnemusic.model.Category;
import com.equipo4.nocturnemusic.model.Product;
import com.equipo4.nocturnemusic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
