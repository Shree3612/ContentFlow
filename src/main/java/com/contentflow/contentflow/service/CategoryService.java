package com.contentflow.contentflow.service;


import com.contentflow.contentflow.Converter.CategoryConverter;
import com.contentflow.contentflow.dto.request.CategoryRequest;
import com.contentflow.contentflow.dto.response.CategoryResponse;
import com.contentflow.contentflow.entities.Category;
import com.contentflow.contentflow.exception.CategoryNotFoundException;
import com.contentflow.contentflow.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
            Category category = CategoryConverter.categoryDtoToCategoryEntity(categoryRequest);
            Category savedCategory = categoryRepository.save(category);
            CategoryResponse response = CategoryConverter.categoryEntityToCategoryResponse(savedCategory);
            return response;
    }

    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category with this ID not present"));

        category.setCategoryTitle(categoryRequest.getCategoryTitle());
        category.setCategoryDescription(categoryRequest.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(category);
        CategoryResponse response = CategoryConverter.categoryEntityToCategoryResponse(updatedCategory);
        return response;
    }

    public CategoryResponse getCategoryById(Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category with this ID not present"));

        CategoryResponse response = CategoryConverter.categoryEntityToCategoryResponse(category);
        return response;
    }

    public List<CategoryResponse> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            responses.add(CategoryConverter.categoryEntityToCategoryResponse(category));
        }
        return responses;
    }

    public String deleteCategory(Integer categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category with this ID not present"));

        categoryRepository.delete(category);
        return "category deleted successfully";
    }
}
