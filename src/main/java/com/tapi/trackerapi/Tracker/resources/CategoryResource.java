package com.tapi.trackerapi.Tracker.resources;

import com.tapi.trackerapi.Tracker.domain.Category;
import com.tapi.trackerapi.Tracker.services.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<Category> category = categoryService.fetchallcategories(userId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")    //  categoryId as uri template variable
    public ResponseEntity<?> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        Category category = categoryService.fetchCategoryById(userId, categoryId);
        return new ResponseEntity<>(Arrays.asList(category), HttpStatus.OK);
    }

    //  add
    @PostMapping("")
    public ResponseEntity<?> addCategory(HttpServletRequest request, @RequestBody Map<String, Object> categoryMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(mapp(category), HttpStatus.CREATED);
    }

    //  update
    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody Category category) {
        int userId = (Integer) request.getAttribute("userId");
        categoryService.updateCategory(userId,categoryId,category);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Map<String, String> mapp(Category category) {
        Map<String, String> map = new HashMap<>();
        //  adding parameters to api response
        map.put("userId", category.getUserId().toString());
        map.put("categoryId", category.getCategoryId().toString());
        map.put("title", category.getTitle());
        map.put("description", category.getDescription());
        return map;
    }
}
