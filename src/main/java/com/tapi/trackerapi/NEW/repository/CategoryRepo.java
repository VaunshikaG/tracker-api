package com.tapi.trackerapi.NEW.repository;

import com.tapi.trackerapi.NEW.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findById(Long userId, Long categoryId);

    Category getById(Long userId, Long categoryId);

    Object deleteById(Long userId, Long categoryId);
}
