package com.tapi.trackerapi.services;

import com.tapi.trackerapi.domain.Category;
import com.tapi.trackerapi.exceptions.TBadRequestException;
import com.tapi.trackerapi.exceptions.TCategoryNotFoundException;
import com.tapi.trackerapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CategoryServiceimpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchallcategories(Integer userId) {
        return null;
    }

    @Override
    public Category fetchCategoryById(Integer userId, Integer categoryId) throws TCategoryNotFoundException {
        return null;
    }

    @Override
    public Category addCategory(Integer userId, String title, String description) throws TBadRequestException {
        //  create which returns primary key
        int categoryId = categoryRepository.create(userId, title, description);
        //  to fetch actual obj
        return categoryRepository.findById(userId, categoryId);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, String description) throws TBadRequestException {

    }

    @Override
    public void deleteCategoryWithAllTrans(Integer userId, Integer categoryId) throws TCategoryNotFoundException {

    }
}
