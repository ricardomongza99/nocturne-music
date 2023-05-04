package com.equipo4.nocturnemusic.repository;

import com.equipo4.nocturnemusic.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
