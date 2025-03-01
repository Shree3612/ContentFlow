package com.contentflow.contentflow.Converter;


import com.contentflow.contentflow.dto.request.CategoryRequest;
import com.contentflow.contentflow.dto.response.CategoryResponse;
import com.contentflow.contentflow.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryConverter {
    public static Category categoryDtoToCategoryEntity(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setCategoryTitle(categoryRequest.getCategoryTitle());
        category.setCategoryDescription(categoryRequest.getCategoryDescription());
        return category;
    }

    public static CategoryResponse categoryEntityToCategoryResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setCategoryTitle(category.getCategoryTitle());
        return categoryResponse;
    }
}
