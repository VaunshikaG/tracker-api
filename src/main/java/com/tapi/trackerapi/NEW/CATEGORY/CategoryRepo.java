package com.tapi.trackerapi.NEW.CATEGORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
