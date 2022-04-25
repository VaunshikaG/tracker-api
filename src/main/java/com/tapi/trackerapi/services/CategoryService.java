package com.tapi.trackerapi.services;

import com.tapi.trackerapi.domain.Category;
import com.tapi.trackerapi.exceptions.TBadRequestException;
import com.tapi.trackerapi.exceptions.TCategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> fetchallcategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws TCategoryNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws TBadRequestException;

    void updateCategory(Integer userId, Integer categoryId, Category category) throws TBadRequestException;

//  cascade delete - to delete category we need to delete all the transactions as 1category can have many transactions
    void deleteCategoryWithAllTrans(Integer userId, Integer categoryId) throws TCategoryNotFoundException;
}
