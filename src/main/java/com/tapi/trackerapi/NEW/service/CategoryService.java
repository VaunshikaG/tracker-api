package com.tapi.trackerapi.NEW.service;

import com.tapi.trackerapi.NEW.model.Category;

import java.util.List;

public interface CategoryService {
    
    Category createCategory(Category category);

    Category updateCategory(Category category, Integer categoryId);

    Category deleteCategory(Integer categoryId);

    List<Category> getCategory();

    Category getCategoryById(Integer categoryId);

}
