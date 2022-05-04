package com.tapi.trackerapi.repository.Category;

//  all curd operations for category

import com.tapi.trackerapi.domain.Category;
import com.tapi.trackerapi.exceptions.TBadRequestException;
import com.tapi.trackerapi.exceptions.TResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findall(Integer userId) throws TResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws TResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws TBadRequestException;

    void update(Integer  userId, Integer categoryId, Category category) throws TBadRequestException;

    void deleteById(Integer userId, Integer categoryId);
}
