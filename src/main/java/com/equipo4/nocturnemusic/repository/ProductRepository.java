package com.equipo4.nocturnemusic.repository;

import com.equipo4.nocturnemusic.model.Category;
import com.equipo4.nocturnemusic.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategory(Category category);
}
