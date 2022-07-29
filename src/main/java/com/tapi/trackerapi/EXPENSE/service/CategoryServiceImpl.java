package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.model.Category;
import com.tapi.trackerapi.EXPENSE.repository.CategoryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category createCategory(Category category) {
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category, Integer categoryId) {
        Category existingCategory = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new TResourceNotFoundException("Category " + categoryId + " not found."));

//        if (categoryRepo.findById(categoryId).isPresent()) {
        existingCategory.setTitle(category.getTitle());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setAmount(category.getAmount());
        Category updated = categoryRepo.save(existingCategory);
        return updated;
//        } else {
//            return null;
//        }
    }

    @Override
    public void deleteById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new TResourceNotFoundException("Category " + categoryId + " not found."));

        categoryRepo.delete(category);
    }

    @Override
    public Category findById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new TResourceNotFoundException("Category " + categoryId + " not found."));
        return category;
    }

    @Override
    public List<Category> allCategory() {
        return categoryRepo.findAll();
    }

}
