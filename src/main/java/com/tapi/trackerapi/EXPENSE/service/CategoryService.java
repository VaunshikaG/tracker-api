package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.model.Category;
import com.tapi.trackerapi.EXPENSE.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto, Integer userId);

    CategoryDto findById(Integer categoryId);

    void deleteById(Integer categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    List<CategoryDto> allCategory();

    List<CategoryDto> categoryByUser(Integer userId);

    List<CategoryDto> searchCategory(String keyword);
}
