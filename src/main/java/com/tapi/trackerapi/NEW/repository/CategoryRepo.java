package com.tapi.trackerapi.NEW.repository;

import com.tapi.trackerapi.NEW.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
