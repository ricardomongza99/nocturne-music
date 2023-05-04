package com.equipo4.nocturnemusic.repository;

import com.equipo4.nocturnemusic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
