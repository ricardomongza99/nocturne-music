package com.equipo4.nocturnemusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.equipo4.nocturnemusic.model.ShoppingCart;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {
	
}