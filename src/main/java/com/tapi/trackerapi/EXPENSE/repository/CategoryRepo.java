package com.tapi.trackerapi.EXPENSE.repository;

import com.tapi.trackerapi.EXPENSE.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
