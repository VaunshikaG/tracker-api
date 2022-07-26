package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.model.Category;
import com.tapi.trackerapi.EXPENSE.repository.CategoryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> allCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category findById(Integer id) {
        if(categoryRepo.findById(id).isPresent()){
            return categoryRepo.findById(id).get();
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Category category = findById(id);
        categoryRepo.delete(category);
    }

    @Override
    public Category updateCategory(Category category, Integer categoryId) {

        if (categoryRepo.findById(categoryId).isPresent()) {
            Category existingCategory = categoryRepo.findById(categoryId).get();

            existingCategory.setTitle(category.getTitle());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setAmount(category.getAmount());

            Category updated = categoryRepo.save(existingCategory);
//            updated.getCategoryId();
//            updated.getTitle();
//            updated.getDescription();
//            updated.getAmount();

            return updated;
        } else {
            return null;
        }
    }

}
