package com.tapi.trackerapi.NEW.controller;

import com.tapi.trackerapi.NEW.model.Category;
import com.tapi.trackerapi.NEW.HELPER.ResponseHandler;
import com.tapi.trackerapi.NEW.EXCEPTION.TResourceNotFoundException;
import com.tapi.trackerapi.NEW.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tracker/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //  get category
    @GetMapping("")
    public ResponseEntity<Object> getAllCategory() {
        try {
            List<Category> result = categoryService.getCategory();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //  get category_by_id
    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory_by_id(
            @PathVariable(value = "categoryId") Integer categoryId) throws TResourceNotFoundException {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, category);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  save/add category
    @PostMapping("")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        try {
//            category.getUser().getUserId();
            Category result = categoryService.createCategory(category);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  update category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable(value = "categoryId") Integer categoryId,
            @RequestBody Category category) throws TResourceNotFoundException {
        try {
            Category category1 = categoryService.updateCategory(category, categoryId);

            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, category1);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "categoryId") Integer categoryId) throws TResourceNotFoundException {
        try {
            Category category = categoryService.deleteCategory(categoryId);
            return ResponseHandler.generateResult("Successfully deleted data!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
