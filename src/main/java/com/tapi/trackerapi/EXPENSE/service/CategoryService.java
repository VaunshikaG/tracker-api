package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> allCategory();

    Category createCategory(Category category);

    Category findById(Integer id);

    void deleteById(Integer id);

    Category updateCategory(Category category, Integer categoryId);

}
