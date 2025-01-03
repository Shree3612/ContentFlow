package com.contentflow.contentflow.controller;


import com.contentflow.contentflow.dto.request.CategoryRequest;
import com.contentflow.contentflow.dto.response.CategoryResponse;
import com.contentflow.contentflow.exception.CategoryNotFoundException;
import com.contentflow.contentflow.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse response = categoryService.createCategory(categoryRequest);
        return response;
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity createCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable("categoryId") int categoryId) {
        try{
            return new ResponseEntity(categoryService.updateCategory(categoryRequest, categoryId), HttpStatus.CREATED);
        }
        catch(CategoryNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable("categoryId") int categoryId) {
        try{
            return new ResponseEntity(categoryService.getCategoryById(categoryId), HttpStatus.OK);
        }
        catch(CategoryNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public List<CategoryResponse> getAllCategory() {
        List<CategoryResponse> responses = categoryService.getAllCategory();
        return responses;
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteUser(@PathVariable("categoryId") Integer categoryId) {
        try{
            return new ResponseEntity(categoryService.deleteCategory(categoryId), HttpStatus.OK);
        }
        catch(CategoryNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
