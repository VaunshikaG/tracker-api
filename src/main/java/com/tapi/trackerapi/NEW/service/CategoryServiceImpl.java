package com.tapi.trackerapi.NEW.service;

import com.tapi.trackerapi.NEW.model.Category;
import com.tapi.trackerapi.NEW.repository.CategoryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public Category createCategory(Category category) {
//        category.setUser(categoryRepo.getById(category.getUser().getUserId()));
        Category category1 = categoryRepo.save(category);
        return category1;
    }

    @Override
    public Category updateCategory(Category category, Integer categoryId) {
        Category updatedCategory = new Category();
        Optional<Category> savedUser = categoryRepo.findById(categoryId);
        Category updateCategory = savedUser.get();
        if (savedUser.isPresent()) {
//            BeanUtils.copyProperties(user, updateUser, Utils.getNullPropertyNames(user));
            BeanUtils.copyProperties(category, updatedCategory);
        }
        updatedCategory = categoryRepo.save(updateCategory);
        return updatedCategory;
    }

    @Override
    public Category deleteCategory(Integer categoryId) {
        Optional<Category> savedCategory = categoryRepo.findById(categoryId);
        if (savedCategory.isPresent()) {
            Category deletedCategory = savedCategory.get();
            categoryRepo.delete(deletedCategory);
        }
        return null;
    }

    @Override
    public List<Category> getCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = new Category();
        Optional<Category> savedCategory = categoryRepo.findById(categoryId);
        if (savedCategory.isPresent())
            return savedCategory.get();
        return category;
    }
}
