package com.tapi.trackerapi.Tracker.services.Category;

import com.tapi.trackerapi.Tracker.domain.Category;
import com.tapi.trackerapi.Tracker.exceptions.TBadRequestException;
import com.tapi.trackerapi.Tracker.exceptions.TResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> fetchallcategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws TResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws TBadRequestException;

    void updateCategory(Integer userId, Integer categoryId, Category category) throws TBadRequestException;

//  cascade delete - to delete category we need to delete all the transactions as 1category can have many transactions
    void deleteCategoryWithAllTrans(Integer userId, Integer categoryId) throws TResourceNotFoundException;
}
