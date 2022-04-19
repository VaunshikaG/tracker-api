package com.tapi.trackerapi.repository;

//  all curd operations for category

import com.tapi.trackerapi.domain.Category;
import com.tapi.trackerapi.exceptions.TBadRequestException;
import com.tapi.trackerapi.exceptions.TCategoryNotFoundException;
import java.util.List;

public interface CategoryRepository {

    List<Category> findall(Integer userId) throws TCategoryNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws TCategoryNotFoundException;

    Integer create(Integer userId, String title, String description) throws TBadRequestException;

    void update(Integer  userId, Integer categoryId, Category category) throws TBadRequestException;

    void deleteById(Integer userId, Integer categoryId);
}
