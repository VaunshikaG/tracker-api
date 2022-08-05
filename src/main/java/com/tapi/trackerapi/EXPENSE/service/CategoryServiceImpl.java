package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.model.Category;
import com.tapi.trackerapi.EXPENSE.model.CategoryDto;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.repository.CategoryRepo;
import com.tapi.trackerapi.EXPENSE.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto, Integer userId) {
//        categoryRepo.save(category);
//        return category;


        User user = this.userRepo.findById(userId).orElseThrow(
                () -> new TResourceNotFoundException("User " + userId + " not found."));

        Category category = this.modelMapper.map(categoryDto, Category.class);
        category.setUser(user);

        Category newCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category existingCategory = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new TResourceNotFoundException("Category " + categoryId + " not found."));

//        if (categoryRepo.findById(categoryId).isPresent()) {
        existingCategory.setTitle(categoryDto.getTitle());
        existingCategory.setDescription(categoryDto.getDescription());
        existingCategory.setAmount(categoryDto.getAmount());
        Category updated = categoryRepo.save(existingCategory);
        return this.modelMapper.map(updated, CategoryDto.class);
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
    public CategoryDto findById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(
                () -> new TResourceNotFoundException("Category " + categoryId + " not found."));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> allCategory() {
        List<CategoryDto> categoryDtos = this.categoryRepo.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> categoryByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new TResourceNotFoundException("User Id not found"));
        List<Category> categoryDtoList = this.categoryRepo.findByUser(user);

        List<CategoryDto> categoryDtos = categoryDtoList.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }

    @Override
    public List<CategoryDto> searchCategory(String keyword) {
        List<Category> categories = this.categoryRepo.searchByTitle("%"+keyword+"%");
        List<CategoryDto> list = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return list;
    }

}
