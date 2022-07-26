package com.tapi.trackerapi.EXPENSE.controller;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.Category;
import com.tapi.trackerapi.EXPENSE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/expense/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    String str = "null";

    //  get category
    @GetMapping("")
    public ResponseEntity<Object> getAllCategory() {
        try {
            List<Category> result = categoryService.allCategory();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  save/add category
    @PostMapping("")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        try {
            Category result = categoryService.createCategory(category);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }


    //  get category_by_id
    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory_by_id(
            @PathVariable Integer categoryId) throws TResourceNotFoundException {
        try {
            Category category = categoryService.findById(categoryId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, category);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }

    //  delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory_by_id(@PathVariable Integer categoryId) throws TResourceNotFoundException {
        try {
            categoryService.deleteById(categoryId);
            return ResponseHandler.generateResult("Successfully deleted data!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }

    //  update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody Category category) throws TResourceNotFoundException {
        try {
            Category category1 = categoryService.updateCategory(category, categoryId);
            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, category1);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, str);
        }
    }

}
