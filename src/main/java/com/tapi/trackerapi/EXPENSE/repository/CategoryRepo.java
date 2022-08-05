package com.tapi.trackerapi.EXPENSE.repository;

import com.tapi.trackerapi.EXPENSE.model.Category;
import com.tapi.trackerapi.EXPENSE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findByUser(User user);

    @Query("select c from Category c where c.title like :key")
    List<Category> searchByTitle(@Param("key") String title);

}
