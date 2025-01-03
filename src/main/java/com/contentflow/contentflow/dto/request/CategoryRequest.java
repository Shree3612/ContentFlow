package com.contentflow.contentflow.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {

    @NotEmpty
    @Size(max = 15, message = "Title must be less 15 characters")
    String categoryTitle;
    @NotEmpty
    @Size(min = 4, max = 100, message = "category description must be minimum 4 characters and maximum 100 characters long")
    String categoryDescription;

    //lombok not working therefore created getters and setters
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
