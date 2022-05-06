package com.tapi.trackerapi.Tracker.repository.Category;

//  all curd operations for category

import com.tapi.trackerapi.Tracker.domain.Category;
import com.tapi.trackerapi.Tracker.exceptions.TBadRequestException;
import com.tapi.trackerapi.Tracker.exceptions.TResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findall(Integer userId) throws TResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws TResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws TBadRequestException;

//    @Query("UPDATE T_CATEGORIES SET TITLE = :TITLE, DESCRIPTION = :DESCRIPTION WHERE USER_ID = :USER_ID AND CATEGORY_ID = :CATEGORY_ID")
    void update(Integer  userId, Integer categoryId, Category category) throws TBadRequestException;

    void deleteById(Integer userId, Integer categoryId);
}
