package com.tapi.trackerapi.NEW.controller;

import com.tapi.trackerapi.NEW.ResponseHandler;
import com.tapi.trackerapi.NEW.exception.TResourceNotFoundException;
import com.tapi.trackerapi.NEW.model.Category;
import com.tapi.trackerapi.NEW.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    //  get category
    @GetMapping("categoryId")
    public ResponseEntity<Object> getAllCategory() {
        try {
            //  this.userRepo.findAll();
            List<Category> result = categoryRepo.findAll();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //  get category_by_id
    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory_by_id(HttpServletRequest request,
                                                @PathVariable("categoryId") Long categoryId) throws TResourceNotFoundException {
        try {
            Long userId = (Long) request.getAttribute("userId");
            Category category = categoryRepo.findById(userId, categoryId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, category);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  save/add category
    @PostMapping("")
    public ResponseEntity<Object> addCategory(
//            HttpServletRequest request, @PathVariable("categoryId") Long categoryId,
                                              @RequestBody Category category) {
        try {
//            Long userId = (Long) request.getAttribute("userId");
            Category result = categoryRepo.save(category);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(HttpServletRequest request,
                                                 @PathVariable("categoryId") Long categoryId,
                                                 @RequestBody Category category) throws TResourceNotFoundException {
        try {
            Long userId = (Long) request.getAttribute("userId");
            Category result = categoryRepo.getById(userId, categoryId);
            result.setTitle(category.getTitle());
            result.setDescription(category.getDescription());
            result.setTotalexpense(category.getTotalexpense());

            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, categoryRepo.save(result));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  delete category
    public ResponseEntity<Object> deleteCategory(HttpServletRequest request,
                                                 @PathVariable("categoryId") Long categoryId,
                                                 @RequestBody Category category) throws TResourceNotFoundException {
        try {
            Long userId = (Long) request.getAttribute("userId");
            Category result = categoryRepo.getById(userId, categoryId);
            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK,
                    categoryRepo.deleteById(userId, categoryId));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
