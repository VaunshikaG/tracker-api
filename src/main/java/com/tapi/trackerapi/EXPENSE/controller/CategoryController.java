package com.tapi.trackerapi.EXPENSE.controller;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.CategoryDto;
import com.tapi.trackerapi.EXPENSE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/expense")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    String str = "null";

    //  save/add category
    @PostMapping("/user/{userId}/category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer userId) throws TResourceNotFoundException {
        try {
            CategoryDto result = this.categoryService.createCategory(categoryDto, userId);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  update category
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@Valid @PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto) throws TResourceNotFoundException {
        try {
            CategoryDto category1 = categoryService.updateCategory(categoryDto, categoryId);
            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, category1);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  delete category
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategory_by_id(@PathVariable Integer categoryId) throws TResourceNotFoundException {
        try {
            categoryService.deleteById(categoryId);
            return ResponseHandler.generateResult("Successfully deleted data!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  get all category
    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() throws TResourceNotFoundException {
        try {
            List<CategoryDto> result = categoryService.allCategory();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  get category_by_id
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getCategory_by_id(
            @PathVariable Integer categoryId) throws TResourceNotFoundException {
        try {
            CategoryDto category = categoryService.findById(categoryId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, category);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }

    //  get by user
    @GetMapping("/user/{userId}/category")
    public ResponseEntity<?> getCategory_by_user(
            @PathVariable Integer userId) throws TResourceNotFoundException {
        try {
            List<CategoryDto> categoryDtoList = this.categoryService.categoryByUser(userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, categoryDtoList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  search
    @GetMapping("/category/search/{keyword}")
    public ResponseEntity<?> search_title(@PathVariable("keyword") String keyword) throws TResourceNotFoundException {
        try {
            List<CategoryDto> result = this.categoryService.searchCategory(keyword);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }

}
